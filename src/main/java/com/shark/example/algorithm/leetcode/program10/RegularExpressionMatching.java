package com.shark.example.algorithm.leetcode.program10;

import com.google.gson.Gson;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        printDp("init", dp);
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                //a*b*c*d*e* 一開始連續的?*因為可以empty，所以可以為ture
                dp[0][i + 1] = true;
            }
        }
        printDp("parser p", dp);
        for (int i = 0; i < s.length(); i++) {
            boolean result = false;
            for (int j = 0; j < p.length(); j++) {
                System.out.println("i: " + i + ", j: " + j);
                System.out.println("s.char: " + s.charAt(i) + ", p.char: " + p.charAt(j));
                String title = "";
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    title = "p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'";
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
//                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                        //Single: [j] - [j + 1] = 1，看前面，前一個值得結果
                        //Multi: [i] - [i + 1] = =1。看上面
                        title = "p.charAt(j) == '*'" +
                                "\n p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.'" +
                                "\ni + 1: " + (i + 1)  + ", j: " + j +
                                "\ni: " + i  + ", j + 1: " + (j + 1) ;
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    } else {
                        //in this case, ?* only counts as empty
                        //[j - 1] - [j + 1] = -2，看前兩個，上一個結果
                        title = "p.charAt(j) == '*'" +
                                "\n p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'" +
                                "\ni + 1: " + (i + 1)  + ", j - 1: " + (j - 1) ;
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
                result = result || dp[i + 1][j + 1];
                printDp(title, dp);
            }
            if(!result) {
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
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        System.out.println("result: " + regularExpressionMatching.isMatch("a", "..*"));
//        System.out.println(regularExpressionMatching.isMatch("a", ".*..a*"));
//        System.out.println(regularExpressionMatching.isMatch2("a", ".*..a*"));
//        System.out.println(regularExpressionMatching.isMatch2("ab", ".*.."));
//        System.out.println(regularExpressionMatching.isMatch("bbbba", ".*a*a"));
//        System.out.println(regularExpressionMatching.isMatch("ab", ".*"));
//        System.out.println(regularExpressionMatching.isMatch2("aaba", "ab*a*c*a"));
    }
}
