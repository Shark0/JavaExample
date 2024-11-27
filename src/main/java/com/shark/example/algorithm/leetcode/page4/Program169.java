package com.shark.example.algorithm.leetcode.page4;

import java.util.HashMap;
import java.util.Map;

public class Program169 {

    public int majorityElement(int[] nums) {
        int major = 0;
        int majorCount = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > majorCount) {
                major = num;
                majorCount = count;
            }
            map.put(num, count);
        }

        return major;
    }

    public static void main(String[] args) {
        Program169 program = new Program169();
        System.out.println(program.majorityElement(new int[]{3, 3, 4}));
    }
}
