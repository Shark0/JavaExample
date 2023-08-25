package com.shark.example.mqtt;

import java.util.ArrayList;
import java.util.List;

public class MqttTcpSubExample2 {

    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_COUNT = 2000;
    private static final String HOST = "tcp://127.0.0.1:55411";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "root";
    private static final String TOPIC = "user/";

    public static void main(String[] argv) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < USER_COUNT; i++) {
            String topic = TOPIC + (USER_ID_START_INDEX + (i % USER_COUNT) + 1);
            MqttSubWorker mqttTcpSubWorker = new MqttSubWorker(HOST, USER_NAME, PASSWORD, topic);
            Thread thread = new Thread(mqttTcpSubWorker);
            threads.add(thread);
            thread.start();
        }

        int liveMileTime = 1000 * 60 * 5;
        while (liveMileTime > 0) {
            try {
                Thread.sleep(10);
                liveMileTime = liveMileTime - 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
