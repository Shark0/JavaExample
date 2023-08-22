package com.shark.example.mqtt;

import org.fusesource.mqtt.client.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MqttSubExample {

    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_COUNT = 100;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 1883;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String TOPIC = "user/";

    public static void main(String[] argv) {
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < USER_COUNT ; i ++) {
            String topic = TOPIC + (USER_ID_START_INDEX + (i % USER_COUNT)  + 1);
            MqttSubWorker mqttSubWorker = new MqttSubWorker(HOST, PORT, USER_NAME, PASSWORD, topic);
            Thread thread = new Thread(mqttSubWorker);
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

        for(Thread thread: threads) {
            thread.interrupt();
        }
    }
}
