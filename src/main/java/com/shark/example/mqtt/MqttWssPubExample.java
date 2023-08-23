package com.shark.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MqttWssPubExample {
    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_COUNT = 100;
    private static final int PUBLISH_TIMES = 10;
    private static final String HOST = "wss://mmschat-mq-dev.hkmpcl.com.hk/ws";
    private static final String USER_NAME = "default_user_SHYBB574Wt6XLIT4d-_";
    private static final String PASSWORD = "Ku-0DBmqplo38xTCOZaBW-bcMhvE12uV";
    private static final String TOPIC = "user/";

    public static void main(String[] argv) {
        String clientId = UUID.randomUUID().toString();
        try {
            MqttClient mqttClient = new MqttClient(HOST, clientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(USER_NAME);
            connectOptions.setPassword(PASSWORD.toCharArray());
            connectOptions.setCleanSession(true);
            connectOptions.setKeepAliveInterval(90);
            connectOptions.setConnectionTimeout(5000);
            connectOptions.setAutomaticReconnect(true);
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
                Thread.sleep(10);
            }
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
