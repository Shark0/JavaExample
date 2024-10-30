package com.shark.example.algorithm.leetcode.page2;

import java.util.HashMap;

public class Program72 {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        printDp(word1, word2, dp);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int leftUp = dp[i - 1][j - 1];
                    int up = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    int step = Math.min(leftUp, Math.min(up, left)) + 1;
                    dp[i][j] = step;
                }
            }
        }
        printDp(word1, word2, dp);

        return dp[word1.length() - 1][word2.length() - 1];
    }

    private void printDp(String word1, String word2, int[][] dp) {
        StringBuilder word2StringBuilder = new StringBuilder();
        word2StringBuilder.append("\t\t ");
        for (int i = 0; i < word2.length(); i++) {
            if (i != 0) {
                word2StringBuilder.append(",");
            }
            word2StringBuilder.append("\" ").append(word2.charAt(i)).append("\"");
        }
        System.out.println(word2StringBuilder);
        int i = 0;
        for (int[] row : dp) {
            StringBuilder rowStringBuilder = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                if (j != 0) {
                    rowStringBuilder.append(",");
                }
                rowStringBuilder.append("\"");
                if (row[j] < 10) {
                    rowStringBuilder.append(" ");
                }
                rowStringBuilder.append(row[j]);
                rowStringBuilder.append("\"");
            }
            if (i == 0) {
                System.out.println("\t" + rowStringBuilder);
            } else {
                char word1Char = word1.charAt(i - 1);
                System.out.println("\"" + word1Char + "\":" + rowStringBuilder);
            }

            i++;
        }
        System.out.println("-------");
    }

    public int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];

        boolean initIsMatch = false;
        char initCompareChar = word2.charAt(0);
        int initStep = 0;
        for (int i = 0; i < dp.length; i++) {
            char c1 = word1.charAt(i);
            if (!initIsMatch && (initCompareChar == c1)) {
                initIsMatch = true;
            } else {
                initStep++;
            }
            dp[i][0] = initStep;
        }
        initIsMatch = false;
        initCompareChar = word1.charAt(0);
        initStep = 0;
        for (int j = 0; j < dp[0].length; j++) {
            char c1 = word2.charAt(j);
            if (!initIsMatch && (initCompareChar == c1)) {
                initIsMatch = true;
            } else {
                initStep++;
            }
            dp[0][j] = initStep;
        }
        printDp2(word1, word2, dp);
        int startI = 1;
        int startJ = 1;
        while (startI < dp.length && startJ < dp[0].length) {
            boolean isMatch = false;
            char compareChar = word2.charAt(startJ);
            for (int i = startI; i < dp.length; i++) {
                char c1 = word1.charAt(i);
                int leftUp = dp[i - 1][startJ - 1];
                int up = dp[i - 1][startJ];
                int left = dp[i][startJ - 1];
                int step = Math.min(leftUp, Math.min(up, left));
                if (!isMatch && (compareChar == c1)) {
                    isMatch = true;
                } else {
                    step++;
                }
                dp[i][startJ] = step;
                System.out.println("i: " + i + ", startJ: " + startJ +
                        ", word1: " + c1 + ", word2: " + compareChar +
                        ", leftUp: " + leftUp + ", up: " + up + ", left: " + left +
                        ", step: " + step);
                printDp2(word1, word2, dp);
            }
            isMatch = false;
            compareChar = word1.charAt(startI);
            for (int j = startJ; j < dp[0].length; j++) {
                char c2 = word2.charAt(j);
                int leftUp = dp[startI - 1][j - 1];
                int up = dp[startI - 1][j];
                int left = dp[startI][j - 1];
                int step = Math.min(leftUp, Math.min(up, left));
                if (!isMatch && (compareChar == c2)) {
                    isMatch = true;
                } else {
                    step++;
                }
                dp[startI][j] = step;
                System.out.println("startI: " + startI + ", j: " + j +
                        ", word1: " + compareChar + ", word2: " + c2 +
                        ", leftUp: " + leftUp + ", up: " + up + ", left: " + left +
                        ", step: " + step);
                printDp2(word1, word2, dp);
            }

            startI++;
            startJ++;
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    public int minDistance1(String word1, String word2) {
        if(word1.isEmpty()) {
            return word2.length();
        }
        if(word2.isEmpty()) {
            return word1.length();
        }
        int[][] dp = new int[word1.length()][word2.length()];
        //init
        boolean initIsMatch = false;
        char initCompareChar = word2.charAt(0);
        int initStep = 0;
        for (int i = 0; i < dp.length; i++) {
            char c1 = word1.charAt(i);
            if (!initIsMatch && (initCompareChar == c1)) {
                initIsMatch = true;
            } else {
                initStep++;
            }
            dp[i][0] = initStep;
        }
        initIsMatch = false;
        initCompareChar = word1.charAt(0);
        initStep = 0;
        for (int j = 0; j < dp[0].length; j++) {
            char c1 = word2.charAt(j);
            if (!initIsMatch && (initCompareChar == c1)) {
                initIsMatch = true;
            } else {
                initStep++;
            }
            dp[0][j] = initStep;
        }
        //compare
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int leftUp = dp[i - 1][j - 1];
                    int up = dp[i - 1][j];
                    int left = dp[i][j - 1];
                    int step = Math.min(leftUp, Math.min(up, left)) + 1;
                    dp[i][j] = step;
                }
            }
        }
        printDp2(word1, word2, dp);
        return dp[word1.length() - 1][word2.length() - 1];
    }

    private void printDp2(String word1, String word2, int[][] dp) {
        StringBuilder word2StringBuilder = new StringBuilder();
        word2StringBuilder.append("\t");
        for (int i = 0; i < word2.length(); i++) {
            if (i != 0) {
                word2StringBuilder.append(",");
            }
            word2StringBuilder.append("\" ").append(word2.charAt(i)).append("\"");
        }
        System.out.println(word2StringBuilder);
        int i = 0;
        for (int[] row : dp) {
            char word1Char = word1.charAt(i);
            StringBuilder rowStringBuilder = new StringBuilder();
            for (int j = 0; j < row.length; j++) {
                if (j != 0) {
                    rowStringBuilder.append(",");
                }
                rowStringBuilder.append("\"");
                if (row[j] < 10) {
                    rowStringBuilder.append(" ");
                }
                rowStringBuilder.append(row[j]);
                rowStringBuilder.append("\"");
            }
            System.out.println("\"" + word1Char + "\":" + rowStringBuilder);
            i++;
        }
        System.out.println("-------");
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println("result: " + new Program72().minDistance1(word1, word2));
    }
}

