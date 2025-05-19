package com.shark.example.algorithm.leetcode.page9;

import java.util.Arrays;

public class Program418 {

    public boolean canPartition(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int currSum = targetSum; currSum >= num; currSum--) {
                dp[currSum] = dp[currSum] || dp[currSum - num];
                if (dp[targetSum]) {
                    return true;
                }
            }
        }
        return dp[targetSum];
    }

    public static void main(String[] args) {
        System.out.println(new Program418().canPartition(new int[]{1, 2, 1, 2}));
    }
}
