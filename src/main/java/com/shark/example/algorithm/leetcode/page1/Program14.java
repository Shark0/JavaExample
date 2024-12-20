package com.shark.example.algorithm.leetcode.page1;

public class Program14 {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        int charIndex = 0;
        while(true) {
            char compareChar = 0;
            for (int i = 0; i < strs.length; i ++) {
                String str = strs[i];
                if(charIndex == str.length()) {
                    return stringBuilder.toString();
                }
                if(i == 0) {
                    compareChar = str.charAt(charIndex);
                } else {
                    char c = str.charAt(charIndex);
                    if(compareChar != c) {
                        return stringBuilder.toString();
                    }
                }
            }
            stringBuilder.append(compareChar);
            charIndex++;
        }
    }

    public static void main(String[] args) {
        Program14 longestCommonPrefix = new Program14();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
