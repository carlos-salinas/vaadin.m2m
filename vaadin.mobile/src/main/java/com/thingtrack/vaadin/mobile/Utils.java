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

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Random;

/**
 * @author Dominik Obermaier
 */
public class Utils {

    private static final Random random = new Random();


    public static String getMacAddress() {
        String result = "";

        try {
            for (NetworkInterface ni : Collections.list(
                    NetworkInterface.getNetworkInterfaces())) {
                byte[] hardwareAddress = ni.getHardwareAddress();

                if (hardwareAddress != null) {
                    for (int i = 0; i < hardwareAddress.length; i++)
                        result += String.format((i == 0 ? "" : "-") + "%02X", hardwareAddress[i]);

                    return result;
                }
            }

        } catch (SocketException e) {
            System.out.println("Could not find out MAC Adress. Exiting Application ");
            System.exit(1);
        }
        return result;
    }

    public static int createRandomNumberBetween(int min, int max) {

        return random.nextInt(max - min + 1) + min;
    }
}
