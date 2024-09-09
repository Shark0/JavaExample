package com.shark.example.algorithm.leetcode.program14;

public class LongestCommonPrefix {
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
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
