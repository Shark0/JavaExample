package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int s3Length = s3.length();
        if (s1Length + s2Length != s3Length) {
            return false;
        }
        if(s1Length == 0) {
            return s2.equals(s3);
        }
        if(s2Length == 0) {
            return s1.equals(s3);
        }
        boolean[][] dp = new boolean[s1Length + 1][s2Length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1Length; i++) {
            dp[i][0] =  dp[i - 1][0] && ((s1.charAt(i - 1) == s3.charAt(i - 1)));
        }
        for (int i = 1; i <= s2Length; i++) {
            dp[0][i] = dp[0][i - 1] && ((s2.charAt(i - 1) == s3.charAt(i - 1)));
        }
        for (int i = 1; i <= s1Length; i++) {
            for (int j = 1; j <= s2Length; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                char c3 = s3.charAt(i + j - 1);
                System.out.println("i: " + i + " j: " + j + " s1.charAt(i - 1): " + c1 +
                        " s2.charAt(j - 1) : " + c2 +
                        " s3.charAt(i + j - 1): " + c3);
                dp[i][j] = (dp[i - 1][j] && (c1 == c3)) ||
                        (dp[i][j - 1] && (c2 == c3));
            }
        }

        printMatrix(dp);
        return dp[s1Length][s2Length];
    }

    private void printMatrix(boolean[][] matrix) {
        System.out.println("===========");
        for (boolean[] rows : matrix) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                if (j > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(rows[j]);
            }
            System.out.println(stringBuilder);
        }
        System.out.println("===========");
    }

    public static void main(String[] args) {
        String s1 = "db";
        String s2 = "b";
        String s3 = "cbb";
        System.out.println(new Program97().isInterleave(s1, s2, s3));
    }
}
