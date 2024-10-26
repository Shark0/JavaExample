package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program72 {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length(); ++i) {
            dp[i][0] = i;
        }
        printDp("handle first column", dp);

        for (int j = 1; j <= word2.length(); ++j) {
            dp[0][j] = j;
        }
        printDp("handle first row", dp);

        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];//no operation
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }

        }

        printDp("final", dp);
        return dp[word1.length()][word2.length()];
    }

    private void printDp(String title, int[][] dp) {
        System.out.println("title: " + title);
        for (int[] row: dp) {
            System.out.println(new Gson().toJson(row));
        }
    }


    public static void main(String[] args) {
        System.out.println("result: " + new Program72().minDistance("horse", "ros"));
    }
}
