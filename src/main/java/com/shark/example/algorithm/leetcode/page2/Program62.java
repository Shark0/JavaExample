package com.shark.example.algorithm.leetcode.page2;

public class Program62 {

    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return multiDp(0, 0, dp);
    }

    private int multiDp(int m, int n, int[][] dp) {
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        int result = 0;
        if (m == dp.length - 1 && n == dp[0].length - 1) {
            result = 0;
        } else if (m == dp.length - 1) {
            result = 1;
        } else if (n == dp[0].length - 1) {
            result = 1;
        } else {
            result = result + multiDp(m + 1, n, dp);
            result = result + multiDp(m, n + 1, dp);
        }

        dp[m][n] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println("result: " + new Program62().uniquePaths(3, 7));
    }
}
