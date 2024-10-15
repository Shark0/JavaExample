package com.shark.example.algorithm.leetcode.page36;

public class Program1768 {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (index < word1.length() || index < word2.length()) {
            Character c1 = null;
            if(index < word1.length()) {
                c1 = word1.charAt(index);
            }
            Character c2 = null;
            if(index < word2.length()) {
                c2 = word2.charAt(index);
            }
            if(c1 != null) {
                result.append(c1);
            }
            if(c2 != null) {
                result.append(c2);
            }
            index++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Program1768 program1768 = new Program1768();
        String result = program1768.mergeAlternately("hello", "world");
        System.out.println(result);
    }
}
