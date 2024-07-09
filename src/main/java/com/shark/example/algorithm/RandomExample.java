package com.shark.example.algorithm;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomExample {
    public static void main(String[] args) {
        List<String> itemList = List.of("item1", "item2", "item3", "item4");
        HashMap<String, Integer> indexResultCountMap = new HashMap<>();
        for(int i = 0; i < 10000000; i++) {
            Random random = new Random();
            int randomIndex = random.nextInt(itemList.size());
            String key = itemList.get(randomIndex);
            int indexRandomCount = indexResultCountMap.computeIfAbsent(key, k -> 0);
            indexRandomCount = indexRandomCount + 1;
            indexResultCountMap.put(key, indexRandomCount);
        }
        Gson gson = new Gson();
        System.out.println("random result: " + gson.toJson(indexResultCountMap));
        String maxItem = "";
        int maxResult = 0;
        for(String key: indexResultCountMap.keySet()) {
            if(indexResultCountMap.get(key) > maxResult) {
                maxResult = indexResultCountMap.get(key);
                maxItem = key;
            }
        }
        System.out.println("max item: " + maxItem);
    }
}
