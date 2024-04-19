package com.shark.example.service.database.redis;

import java.util.HashSet;
import java.util.Set;

public class RedisSetExample {

    public static void main(String[] argv) {
        RedisSetWorker redisSetWorker = new RedisSetWorker();
        Set<String> addSet = new HashSet<>();
        for(int i = 0; i < 10000; i ++) {
            addSet.add("User" + i);
        }
        String key = "Chatroom1001";
        redisSetWorker.addSet(key, addSet);
        redisSetWorker.printSet(key);
        Set<String> removeSet = new HashSet<>();
        for(int i = 0; i < 5000; i ++) {
            removeSet.add("User" + i);
        }
        redisSetWorker.removeSet(key, removeSet);
        redisSetWorker.printSet(key);

    }
}
