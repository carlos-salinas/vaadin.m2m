package com.thingtrack.vaadin.mobile;

/*
 * #%L
 * Vaadin TouchKit Web Application
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

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@Theme("mobiletheme")
@Widgetset("com.thingtrack.vaadin.mobile.gwt.AppWidgetSet")
@Title("My Mobile App")
public class MyVaadinApplication extends UI {
	private VerticalLayout mainLayout;

	@Override
	protected void init(VaadinRequest request) {

		mainLayout = new VerticalLayout();
		mainLayout.setSizeFull();
		mainLayout.setMargin(true);
		setContent(mainLayout);
		mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Label loadingText = new Label("MESSAGES RECEIVED: ");
		loadingText.setSizeUndefined();
		mainLayout.addComponent(loadingText);
		
		Subscriber subscriber = new Subscriber();
		subscriber.start(new MqttCallback() {
			
			@Override
			public void messageArrived(MqttTopic topic, MqttMessage message)
					throws Exception {
				
		    	System.out.println("Message arrived. Topic: " + topic.getName() + "  Message: " + message.toString());
		    	
		    	Label loadingText = new Label(message.toString());
				loadingText.setSizeUndefined();
				mainLayout.addComponent(loadingText);
			}
			
			@Override
			public void deliveryComplete(MqttDeliveryToken arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connectionLost(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void markAsDirty() {
		// TODO Auto-generated method stub

	}

}
