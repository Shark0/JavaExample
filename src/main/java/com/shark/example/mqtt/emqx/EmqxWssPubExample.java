package com.shark.example.mqtt.emqx;



import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EmqxWssPubExample {


    private static final String HOST = "wss://mqttws-emqx-group-chat-dev.hkmpcl.com.hk/mqtt";
    private static final String TOPIC = "hktv/group_chat/chatroom/999";

    public static void main(String[] argv) {
        String clientId = UUID.randomUUID().toString();
        try {
            MqttClient mqttClient = new MqttClient(HOST, clientId);

            MqttConnectionOptions connectOptions = new MqttConnectionOptions();
            connectOptions.setUserName("acl_test");
            connectOptions.setPassword("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2wiOnsic3ViIjpbImhrdHYvZ3JvdXBfY2hhdC91c2VyL0hIY3liZXJfc2VjdXJpdHkiLCJoa3R2L2dyb3VwX2NoYXQvY2hhdHJvb20vIyJdfSwiaWF0IjoxNzA4MDQ2OTMxLCJleHAiOjE3MTY2ODY5MzF9.MHZmGbQCEbPayr1MQaU-bWRMT31EGMKnLBBII_rGDQlRpkxVTykzhSP9b0CqnAM43BQQF-lKWXD9HTbTjFfCGEGg98gzIDLz23Qh9nPk7cnn1jHeSDdQmgOtPZlRS69BM3v7xwIyl18_7deQwlHZRqigdQeDNW7ECUXr2HFR13B2zx11m4QZJVBSEXxKBchOL-57oLy67WHqNKABA0ske8m7-N8DGttzPVTtnAZsViOETJUDEV6hASd8d-82w_8gK8Brs1DknTtFPsRxOErGDWrrS92A45ZHDun88j052NAWVCGyoc7gqDzC62Ycf9zSeUVu0bLIbCNnBQ5dHUs8qA".getBytes());
            connectOptions.setKeepAliveInterval(15);
            connectOptions.setConnectionTimeout(5000);
            connectOptions.setAutomaticReconnect(true);
            mqttClient.connect(connectOptions);

            for(int i = 0; i < 1; i ++) {
                MqttTopic mqttTopic = mqttClient.getTopic(TOPIC);
                String message = "MESSAGE" + i;
                MqttMessage mqttMessage = new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
                mqttMessage.setQos(0);
                MqttToken token = mqttTopic.publish(mqttMessage);
                System.out.println("topic = " + TOPIC + ", message id = " + token.getMessageId() + ", message = " + message + ", sendTime = " + System.currentTimeMillis());

            }
            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
