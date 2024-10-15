package com.shark.example.algorithm.leetcode.page8;

public class Program392 {

    public boolean isSubsequence(String s, String t) {
        if(s.isEmpty()) {
            return true;
        }
        if(t.isEmpty()) {
            return false;
        }
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            char sChar = s.charAt(sIndex);
            char tChar = t.charAt(tIndex);
            if (sChar == tChar) {
                sIndex++;
            }
            if(sIndex == s.length()) {
                return true;
            }
            tIndex++;
        }
        return false;
    }

    public static void main(String[] args) {
        Program392 program392 = new Program392();
        System.out.println(program392.isSubsequence("bcb", "abcbacba"));
    }
}
