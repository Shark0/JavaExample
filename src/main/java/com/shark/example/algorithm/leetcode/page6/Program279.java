package com.shark.example.algorithm.leetcode.page6;

import com.google.gson.Gson;

public class Program279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        int max = (int) Math.sqrt(n);
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = max; j >= 1; j--) {
                int value = j * j;
                int nextIndex = i - value;
                if (nextIndex < 0) {
                    continue;
                }
                int previewNextIndexStep = dp[nextIndex];
                if (previewNextIndexStep == 0) {
                    dp[nextIndex] = dp[i] + 1;
                } else {
                    int currentNextIndexStep = dp[i] + 1;
                    if (currentNextIndexStep < previewNextIndexStep) {
                        dp[nextIndex] = currentNextIndexStep;
                    }
                }
//                System.out.println("i: " + i + ", j: " + j + ", value: " + value +
//                        ", nextIndex: " + nextIndex + ", dp: " + new Gson().toJson(dp));
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new Program279().numSquares(3));
    }
}
