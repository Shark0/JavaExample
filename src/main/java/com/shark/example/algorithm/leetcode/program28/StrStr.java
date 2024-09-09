package com.shark.example.algorithm.leetcode.program28;

public class StrStr {
    public int strStr(String haystack, String needle) {
        int startIndex;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0;

            while(j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                j ++;
            }

            startIndex = i;
            if (j == needle.length()) {
                return startIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        System.out.println(strStr.strStr("hello", "llo"));
    }
}

