package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program44 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i]) {
                dp[0][i + 1] = true;
            }
        }
        printDp("init", dp);

        for (int i = 0; i < s.length(); i++) {
            boolean rowResult = false;
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
                rowResult = rowResult || dp[i + 1][j + 1];
                String title = "i: " + i + ", s.charAt(i): " + s.charAt(i) +
                        ", j: " + j + ", p.charAt(j): " + p.charAt(j);
                printDp(title, dp);
            }
            if (!rowResult) {
                return false;
            }
        }

        return dp[s.length()][p.length()];
    }

    public void printDp(String title, boolean[][] dp) {
        System.out.println("title: " + title);
        Gson gson = new Gson();
        for (boolean[] row : dp) {
            System.out.println(gson.toJson(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Program44 program44 = new Program44();
        String testS = "abcabczzzde";
        String testP = "*abc???de*";
        System.out.println("testS: " + testS + ", testP: " + testP);
        boolean result = program44.isMatch(testS, testP);
        System.out.println("result: " + result);
    }
}
