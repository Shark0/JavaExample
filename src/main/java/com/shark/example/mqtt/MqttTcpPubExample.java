package com.shark.example.mqtt;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import java.nio.charset.StandardCharsets;

public class MqttTcpPubExample {

    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_COUNT = 100;
    private static final int PUBLISH_TIMES = 10;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1883;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String TOPIC = "user/";

    public static void main(String[] argv) {
        MQTT mqtt = new MQTT();
        try {
            mqtt.setHost(HOST, PORT);
            mqtt.setUserName(USER_NAME);
            mqtt.setPassword(PASSWORD);
            BlockingConnection connection = mqtt.blockingConnection();
            connection.connect();
            for(int i = 0; i < USER_COUNT * PUBLISH_TIMES; i ++) {
                System.out.println("index = " + (i % USER_COUNT) + ", i = " + i + ", userCount = " + USER_COUNT);
                String topic = TOPIC + (USER_ID_START_INDEX + (i % USER_COUNT) + 1);
                String message = "MESSAGE" + i;
                connection.publish(topic, message.getBytes(StandardCharsets.UTF_8), QoS.AT_LEAST_ONCE, false);
                System.out.println("topic = " + topic + ", message = " + message + ", sendTime = " + System.currentTimeMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
