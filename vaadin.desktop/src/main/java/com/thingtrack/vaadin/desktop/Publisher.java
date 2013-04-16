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

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


public class Publisher {

    public static final String BROKER_URL = "tcp://m2m.eclipse.org:1883";
    public static final String VAADIN_DESKTOP_TOPIC_NOTIFICATION= "com.thingtrack/vaadin/notification";
    
    private MqttClient client;


    public Publisher() {

        //We have to generate a unique Client id.
        String clientId = Utils.getMacAddress() + "-pub";

        try {

            client = new MqttClient(BROKER_URL, clientId);
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {

        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setWill(client.getTopic("com.thingtrack/vaadin/LWT"), "I'm gone :(".getBytes(), 0, false);            
            client.connect(options);
      
        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void send(String message) throws MqttException {
        
    	final MqttTopic notificationTopic = client.getTopic(VAADIN_DESKTOP_TOPIC_NOTIFICATION);

        notificationTopic.publish(new MqttMessage(message.getBytes()));
        System.out.println("Published data. Topic: " + notificationTopic.getName() + "   Message: " + message);
    }
}
