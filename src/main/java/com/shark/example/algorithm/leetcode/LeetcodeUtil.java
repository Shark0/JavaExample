package com.shark.example.algorithm.leetcode;

public class LeetcodeUtil {

    public static void print2dIntArray(int[][] dp) {
        for (int[] row : dp) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                if(i > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(String.format("%2d", row[i]));
            }
            System.out.println(stringBuilder);
        }
    }


    public void convertArrayInput() {
        String original = "[[-1,1,2,-1],[2,13,15,-1],[-1,10,-1,-1],[-1,6,2,8]]";
        original = original.replace("[", "{").replace("]", "}");
        System.out.println(original);
    }

    public static void main(String[] args) {
        new LeetcodeUtil().convertArrayInput();
    }
}
