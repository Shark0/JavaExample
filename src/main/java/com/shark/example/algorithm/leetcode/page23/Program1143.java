package com.shark.example.algorithm.leetcode.page23;

import com.google.gson.Gson;

public class Program1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] multiDp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    multiDp[i][j] = multiDp[i - 1][j - 1] + 1;
                } else {
                    multiDp[i][j] = Math.max(multiDp[i][j - 1], multiDp[i - 1][j]);
                }
            }
        }
        return multiDp[text1.length()][text2.length()];
    }

    private void printDp(int[][] multiDp) {
        System.out.println("dp:");
        for (int[] dp : multiDp) {
            System.out.println("dp: " + new Gson().toJson(dp));
        }
    }

    public static void main(String[] args) {
        System.out.println("result: " + new Program1143().longestCommonSubsequence("abc", "ac"));
    }
}
