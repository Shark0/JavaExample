package com.shark.example.algorithm.leetcode.page2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Program75 {

    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            count ++;
            map.put(num, count);
        }

        int index = 0;
        for (int i = 0; i < 3; i++) {
            int count = map.getOrDefault(i, 0);
            for (int j = 0; j < count; j++) {
                nums[index] = i;
                index ++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        new Program75().sortColors(nums);
        System.out.printf("nums = %s ", Arrays.toString(nums));
    }
}
