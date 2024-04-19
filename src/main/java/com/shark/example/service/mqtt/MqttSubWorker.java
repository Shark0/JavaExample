package com.shark.example.service.mqtt;



import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

import java.util.UUID;

public class MqttSubWorker implements Runnable {

    private String host;
    private String user;
    private String password;
    private String topic;

    public boolean isConnect;

    public MqttSubWorker(String host, String user, String password, String topic) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.topic = topic;
    }


    @Override
    public void run() {
        String clientId = UUID.randomUUID() + "_" + topic;
        System.out.println("clientId = " + clientId);
        try {
            MqttClient mqttClient = new MqttClient(host, clientId);
            MqttConnectionOptions connectOptions = new MqttConnectionOptions();
            connectOptions.setUserName(user);
            connectOptions.setPassword(password.getBytes());
            connectOptions.setKeepAliveInterval(15);
            connectOptions.setConnectionTimeout(60000);
            connectOptions.setAutomaticReconnect(false);
            mqttClient.connect(connectOptions);
            isConnect = true;

            mqttClient.setCallback(new MqttCallback() {


                @Override
                public void disconnected(MqttDisconnectResponse mqttDisconnectResponse) {

                }

                @Override
                public void mqttErrorOccurred(MqttException e) {

                }

                @Override
                public void messageArrived(String topics, MqttMessage mqttMessage) throws Exception {
                    System.out.println("topic = " + topic +
                            ", messageId = " + mqttMessage.getId() +
                            ", message = " + new String(mqttMessage.getPayload()) +
                            ", time = " + System.currentTimeMillis());
                }

                @Override
                public void deliveryComplete(IMqttToken iMqttToken) {

                }

                @Override
                public void connectComplete(boolean b, String s) {

                }

                @Override
                public void authPacketArrived(int i, MqttProperties mqttProperties) {

                }


            });
            mqttClient.subscribe(topic, 0);
        } catch (Exception e) {
            System.out.println("topic: " + topic + ", error = " + e);
        }
    }
}
