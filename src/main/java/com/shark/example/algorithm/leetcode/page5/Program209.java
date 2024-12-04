package com.shark.example.algorithm.leetcode.page5;

public class Program209 {

    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int j = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                min = Math.min(min, i - j + 1);
                sum = sum - nums[j];
                j++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        System.out.println(new Program209().minSubArrayLen(11, new int[]{1, 2, 3, 4, 5}));
    }
}
