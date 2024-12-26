package com.shark.example.algorithm.leetcode.page4;

public class Program188 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        //if k >= n/2, then you can make maximum number of transactions.
        if (k >= n / 2) {
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    max += prices[i] - prices[i - 1];
            }
            return max;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int max = dp[i - 1][0] - prices[0];
            System.out.println("max: " + max);
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + max);
                max = Math.max(max, dp[i - 1][j] - prices[j]);
//                System.out.println("i: " + i + ", j: " + j + ", max: " + max + ", dp[i][j]: " + dp[i][j]);
            }
        }
//        printDp(dp);
        return dp[k][n - 1];
    }

    private void printDp(int[][] dp) {
        for (int[] row : dp) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(String.format("%d", row[j]));
            }
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Program188().maxProfit(3, new int[]{1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4}));
    }
}
