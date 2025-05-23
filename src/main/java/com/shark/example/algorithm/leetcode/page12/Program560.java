package com.shark.example.algorithm.leetcode.page12;

import com.google.gson.Gson;

import java.util.HashMap;

public class Program560 {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> subMap = new HashMap<>();
        subMap.put(0, 1);
        int total = 0;
        int count = 0;

        for (int num : nums) {
            total = total + num;
            if (subMap.containsKey(total - k)) {
                count = count + subMap.get(total - k);
            }
            subMap.put(total, subMap.getOrDefault(total, 0) + 1);
            System.out.println("num = " + num + ", total = " + total + ", count = " + count);
            System.out.println(new Gson().toJson(subMap));
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Program560().subarraySum(new int[]{1, 2, 1, 2, 1}, 3));
    }
}
