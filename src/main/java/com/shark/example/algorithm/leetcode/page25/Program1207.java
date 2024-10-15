package com.shark.example.algorithm.leetcode.page25;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Program1207 {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> valueCountMap = new HashMap<>();
        for(int value: arr) {
            int count = valueCountMap.getOrDefault(value, 0);
            count++;
            valueCountMap.put(value, count);
        }
        Set<Integer> countSet = new HashSet<>();
        for(Integer value: valueCountMap.keySet()) {
            Integer count = valueCountMap.get(value);
            if(countSet.contains(count)) {
                return false;
            }
            countSet.add(count);
        }
        return true;
    }

    public static void main(String[] args) {
        Program1207 program1207 = new Program1207();
        int[] arr = new int[]{1, 2, 2, 1, 1, 3};
        boolean result = program1207.uniqueOccurrences(arr);
        System.out.println("result: " + result);
    }
}
