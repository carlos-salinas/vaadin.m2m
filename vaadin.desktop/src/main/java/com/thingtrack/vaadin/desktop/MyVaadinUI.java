package com.thingtrack.vaadin.desktop;

/*
 * #%L
 * Vaadin Web Application
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2013 Thingtrack S.L.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.eclipse.paho.client.mqttv3.MqttException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	
	private VerticalLayout mainLayout;
	private TextArea textArea;
	private Publisher publisher;
	
	@Override
	protected void init(VaadinRequest request) {

		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		setContent(mainLayout);

		mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		textArea = new TextArea();
		textArea.setInputPrompt("Send message");
		mainLayout.addComponent(textArea);
		
		Button button = new Button("Send");
		button.setImmediate(true);
		button.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				if(publisher == null){
					publisher = new Publisher();
					publisher.start();
				}
				
				String message = textArea.getValue();
				
				try {
					publisher.send(message);
				} catch (MqttException e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
		});
		
		mainLayout.addComponent(button);
	}
}
