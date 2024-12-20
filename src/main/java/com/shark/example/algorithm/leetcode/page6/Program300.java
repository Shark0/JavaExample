package com.shark.example.algorithm.leetcode.page6;

import java.util.Arrays;

public class Program300 {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Program300().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
