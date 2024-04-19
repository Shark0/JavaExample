package com.shark.example.service.database.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisSetWorker {

    private final Jedis jedis;

    public RedisSetWorker() {
        jedis = new Jedis();
        System.out.println("jedis.ping(): " + jedis.ping());
    }

    public void addSet(String key, Set<String> set) {
        long startTime = System.currentTimeMillis();
        jedis.sadd(key, set.toArray(new String[0]));
        long endTime = System.currentTimeMillis();
        System.out.println("addSet totalTime = " + (endTime - startTime));

    }

    public void printSet(String key) {
        long startTime = System.currentTimeMillis();
        System.out.println("printSet size = " + jedis.smembers(key).size());
        long endTime = System.currentTimeMillis();
        System.out.println("printSet totalTime = " + (endTime - startTime));
    }

    public void removeSet(String key, Set<String> set) {
        long startTime = System.currentTimeMillis();
        jedis.srem(key, set.toArray(new String[0]));
        long endTime = System.currentTimeMillis();
        System.out.println("removeSet totalTime = " + (endTime - startTime));
    }

}
