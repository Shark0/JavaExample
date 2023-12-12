package com.shark.example.mqtt.emqx;

import com.shark.example.mqtt.MqttSubWorker;

public class EmqxWssSubExample {


    private static final String HOST = "wss://mqttws-emqx-group-chat-dev.hkmpcl.com.hk/mqtt";
    private static final String USER_NAME = "";
    private static final String TOPIC = "hktv/group_chat/chatroom/5";

    public static void main(String[] argv) {
        String password = new EmqxJwtExample().generate();
        System.out.println("password = " + password);
        MqttSubWorker mqttTcpSubWorker = new MqttSubWorker(HOST, USER_NAME, password, TOPIC);
        mqttTcpSubWorker.run();


        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
