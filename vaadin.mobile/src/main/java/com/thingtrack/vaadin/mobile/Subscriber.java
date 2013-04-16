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
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author Dominik Obermaier
 */
public class Subscriber {

    public static final String BROKER_URL = "tcp://m2m.eclipse.org:1883";

    //We have to generate a unique Client id.
    String clientId = Utils.getMacAddress() + "-sub";
    private MqttClient mqttClient;

    public Subscriber() {

        try {
            mqttClient = new MqttClient(BROKER_URL, clientId);


        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start(MqttCallback callback) {
        try {

            mqttClient.setCallback(callback);
            mqttClient.connect();

            //Subscribe to all subtopics of com.thingtrack/vaadin-desktop/*
            mqttClient.subscribe("com.thingtrack/vaadin/#");

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
