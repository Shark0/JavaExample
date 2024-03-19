package com.shark.example.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisHashExample {
    public static void main(String[] argv) {
        RedisHashWorker redisHashWorker = new RedisHashWorker();
        Set<String> filedSet = new HashSet<>();
        for(int i = 0; i < 10000; i ++) {
            filedSet.add("User" + i);
        }
        Map<String, String> filedValueMap = new HashMap<>();
        for(String key: filedSet) {
            filedValueMap.put(key, "online");
        }
        String key = "Chatroom1";
        redisHashWorker.pushHashSet(key, filedValueMap);
        redisHashWorker.printHashSet(key);
        Set<String> deleteFiledSet = new HashSet<>();
        for(int i = 0; i < 5000; i ++) {
            deleteFiledSet.add("User" + i);
        }
        redisHashWorker.deleteHashSet(key, deleteFiledSet);
        redisHashWorker.printHashSet(key);

        Set<String> keySet = new HashSet<>();
        for(int i = 0; i < 100; i ++) {
            keySet.add("Chatroom" + i);
        }
        redisHashWorker.pushHashSet(keySet, "User1", "online");
    }
}
