package com.shark.example.algorithm.leetcode.page3;

import java.util.HashMap;

public class Program136 {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
            System.out.println("result: " + result);
        }
        return result;
    }

    public int singleNumberV1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            count++;
            map.put(num, count);
        }
        for (int num : map.keySet()) {
            int count = map.get(num);
            if (count == 1) {
                return num;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("result: " + new Program136().singleNumber(new int[]{3, 2, 1, 3, 2}));
    }
}
