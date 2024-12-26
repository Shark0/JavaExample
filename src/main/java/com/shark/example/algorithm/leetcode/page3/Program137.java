package com.shark.example.algorithm.leetcode.page3;

import java.util.HashMap;
import java.util.Map;

public class Program137 {

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Program137().singleNumber(new int[]{1, 2, 2, 1, 3}));
    }
}
