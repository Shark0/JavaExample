package com.shark.example.algorithm.leetcode.page2;

import java.util.HashMap;
import java.util.Map;

public class Program80 {

    public int removeDuplicates(int[] nums) {
        int count = 0;
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer numCount = numCountMap.getOrDefault(num, 0);
            numCount ++;
            if(numCount > 2) {
                count++;
            }
            numCountMap.put(num, numCount);
            nums[i - count] = num;
        }
        return nums.length - count;
    }

    public static void main(String[] args) {
        Program80 program = new Program80();
        System.out.println(program.removeDuplicates(new int[]{1, 1, 1, 2, 3, 3, 4, 4, 4}));
    }
}
