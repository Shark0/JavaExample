package com.shark.example.algorithm.leetcode.page16;

public class Program790 {

    public int numTilings(int n) {
        long[] dp = new long[n + 3];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 5;
        for (int i = 3; i < n; i ++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % 1000000007;
        }
        return (int)dp[n - 1];
    }

    public static void main(String[] args) {
        //https://leetcode.cn/problems/domino-and-tromino-tiling/solutions/1968516/by-endlesscheng-umpp/
        int n = 4;
        System.out.println("result: " + new Program790().numTilings(n));
    }
}
