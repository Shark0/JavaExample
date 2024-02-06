package com.shark.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MqttWssPubExample {
    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_COUNT = 1000;
    private static final int PUBLISH_TIMES = 10;
    private static final String HOST = "wss://127.0.0.1/ws";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "root";
    private static final String TOPIC = "user/";

    public static void main(String[] argv) {
        try {
            MqttClient mqttClient = new MqttClient(HOST, UUID.randomUUID().toString());
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(USER_NAME);
            connectOptions.setPassword(PASSWORD.toCharArray());
            connectOptions.setCleanSession(true);
            connectOptions.setKeepAliveInterval(90);
            connectOptions.setConnectionTimeout(5000);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setMaxInflight(1000000);
            connectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            mqttClient.connect(connectOptions);

            for(int i = 0; i < USER_COUNT * PUBLISH_TIMES; i ++) {
                System.out.println("index = " + (i % USER_COUNT) + ", i = " + i + ", userCount = " + USER_COUNT);
                String topic = TOPIC + (USER_ID_START_INDEX + (i % USER_COUNT) + 1);
                MqttTopic mqttTopic = mqttClient.getTopic(topic);
                String message = "MESSAGE" + i;
                MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
                mqttTopic.publish(mqttMessage);
                System.out.println("topic = " + topic + ", message = " + message + ", sendTime = " + System.currentTimeMillis());
            }
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
