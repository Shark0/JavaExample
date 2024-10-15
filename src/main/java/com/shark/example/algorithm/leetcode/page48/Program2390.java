package com.shark.example.algorithm.leetcode.page48;

public class Program2390 {

    public String removeStars(String s) {
        int index = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '*') {
                index --;
            } else {
                chars[index] = chars[i];
                index ++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder ();
        for(int i = 0; i < index; i++) {
            stringBuilder.append(chars[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Program2390 program2390 = new Program2390();
        String result = program2390.removeStars("erase*****");
        System.out.println(result);
    }
}
