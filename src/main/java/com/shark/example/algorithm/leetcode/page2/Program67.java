package com.shark.example.algorithm.leetcode.page2;

public class Program67 {


    public String addBinary(String a, String b) {
        int length = Math.max(a.length(), b.length());
        StringBuilder result = new StringBuilder();
        int temp = 0;
        for (int i = 0; i < length; i++) {
            int value1;
            if(i < a.length()) {
                value1 = Integer.parseInt(a.charAt(a.length() - i - 1) + "");
            } else {
                value1 = 0;
            }
            int value2;
            if(i < b.length()) {
                value2 = Integer.parseInt(b.charAt(b.length() - i - 1) + "");
            } else {
                value2 = 0;
            }

            temp = temp + value1 + value2;
            result.insert(0, temp % 2);
            temp = temp / 2;
        }
        if(temp == 1) {
            result.insert(0, 1);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Program67().addBinary("1010", "1011"));
    }
}
