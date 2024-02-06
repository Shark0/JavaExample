package com.shark.example.mqtt.emqx;

import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EmqxWssPubExample {


    private static final String HOST = "wss://mqttws-emqx-group-chat-dev.hkmpcl.com.hk/mqtt";
    private static final String TOPIC = "hktv/group_chat/chatroom/999";

    public static void main(String[] argv) {
        String clientId = UUID.randomUUID().toString();
        try(MqttClient mqttClient = new MqttClient(HOST, clientId)) {
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName("server");
            connectOptions.setPassword("server".toCharArray());
            connectOptions.setCleanSession(true);
            connectOptions.setKeepAliveInterval(15);
            connectOptions.setConnectionTimeout(5000);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setMaxInflight(1000000);
            connectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            mqttClient.connect(connectOptions);

            for(int i = 0; i < 65536 * 4; i ++) {
                MqttTopic mqttTopic = mqttClient.getTopic(TOPIC);
                String message = "MESSAGE" + i;
                MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
                mqttMessage.setQos(0);
                MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
                System.out.println("topic = " + TOPIC + ", message id = " + token.getMessageId() + ", message = " + message + ", sendTime = " + System.currentTimeMillis());

            }
            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
