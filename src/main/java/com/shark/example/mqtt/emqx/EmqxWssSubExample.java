package com.shark.example.mqtt.emqx;

import com.shark.example.mqtt.MqttSubWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmqxWssSubExample {


    private static final String HOST = "wss://mqttws-emqx-group-chat-dev.hkmpcl.com.hk/mqtt";
    private static final String USER_NAME = "";
    private static final String TOPIC = "hktv/group_chat/chatroom/999";

    public static void main(String[] argv) {
        String password = new EmqxJwtExample().generate();
        System.out.println("password = " + password);
        MqttSubWorker mqttTcpSubWorker = new MqttSubWorker(HOST, USER_NAME, password, TOPIC);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("start connection time: " + simpleDateFormat.format(new Date()));
        mqttTcpSubWorker.run();

        while (mqttTcpSubWorker.isConnect) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end connection time: " + simpleDateFormat.format(new Date()));
    }
}
