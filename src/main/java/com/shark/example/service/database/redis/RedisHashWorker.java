package com.shark.example.service.database.redis;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class RedisHashWorker {

    private final Jedis jedis;

    public RedisHashWorker() {
        jedis = new Jedis();
        System.out.println("jedis.ping(): " + jedis.ping());
    }

    public void pushHashSet(String key, Map<String, String> map) {
        long startTime = System.currentTimeMillis();
        for(String filed: map.keySet()) {
            String value = map.get(filed);
            jedis.hset(key, filed, value);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("pushHashSet totalTime = " + (endTime - startTime));

    }

    public void pushHashSet(Set<String> keySet, String filed, String value) {
        long startTime = System.currentTimeMillis();
        for(String key: keySet) {
            jedis.hset(key, filed, value);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("pushHashSet totalTime = " + (endTime - startTime));

    }

    public void deleteHashSet(String key, Set<String> filedSet) {
        long startTime = System.currentTimeMillis();
        jedis.hdel(key, filedSet.toArray(String[]::new));
        long endTime = System.currentTimeMillis();
        System.out.println("deleteHashSet totalTime = " + (endTime - startTime));
    }

    public void printHashSet(String key) {
        long startTime = System.currentTimeMillis();
        int size = jedis.hgetAll(key).size();
        System.out.println("hash set size = " + size);
        long endTime = System.currentTimeMillis();
        System.out.println("printHashSet totalTime = " + (endTime - startTime));
    }

}
