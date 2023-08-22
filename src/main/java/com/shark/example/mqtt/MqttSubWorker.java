package com.shark.example.mqtt;

import org.fusesource.mqtt.client.*;

import java.util.concurrent.TimeUnit;

public class MqttSubWorker implements Runnable {

    private String host;
    private int port;
    private String user;
    private String password;
    private String topic;

    public MqttSubWorker(String host, int port, String user, String password, String topic) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.topic = topic;
    }


    @Override
    public void run() {
        System.out.println("topic = " + topic);
        try {
            MQTT mqtt = new MQTT();
            mqtt.setHost(host, port);
            mqtt.setUserName(user);
            mqtt.setPassword(password);
            BlockingConnection connection = mqtt.blockingConnection();
            connection.connect();
            Topic[] topics = {new Topic(topic, QoS.EXACTLY_ONCE)};
            connection.subscribe(topics);
            while (true) {
                Message message = connection.receive(10, TimeUnit.MILLISECONDS);
                if (message != null) {
                    System.out.println("topic = " + topic + ", message = " + new String(message.getPayload()) +
                            ", time = " + System.currentTimeMillis());
                    message.ack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
