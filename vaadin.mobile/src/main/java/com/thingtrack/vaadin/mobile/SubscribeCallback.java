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

import com.vaadin.ui.Field;

public class SubscribeCallback implements MqttCallback {

    private Field field;
	
	
    public void setField(Field field) {
		this.field = field;
	}

	@Override
    public void connectionLost(Throwable cause) {
        //This is called when the connection is lost. We could reconnect here.
    }

    @Override
    public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception {
        
    	System.out.println("Message arrived. Topic: " + topic.getName() + "  Message: " + message.toString());

    	if(field != null)
    		field.setValue(new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken token) {
        //no-op
    }
}
