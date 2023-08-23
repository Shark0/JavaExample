package com.shark.example.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.fusesource.mqtt.client.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MqttWssSubWorker implements Runnable {

    private String host;
    private String user;
    private String password;
    private String topic;

    public MqttWssSubWorker(String host, String user, String password, String topic) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.topic = topic;
    }


    @Override
    public void run() {
        System.out.println("topic = " + topic);
        String clientId = UUID.randomUUID().toString();
        try {
            MqttClient mqttClient = new MqttClient(host, clientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setUserName(user);
            connectOptions.setPassword(password.toCharArray());
            connectOptions.setCleanSession(true);
            connectOptions.setKeepAliveInterval(90);
            connectOptions.setConnectionTimeout(5000);
            connectOptions.setAutomaticReconnect(true);
            connectOptions.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            mqttClient.connect(connectOptions);

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String topics, MqttMessage mqttMessage) throws Exception {
                    System.out.println("topic = " + topic + ", message = " + new String(mqttMessage.getPayload()) +
                            ", time = " + System.currentTimeMillis());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            mqttClient.subscribe(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
