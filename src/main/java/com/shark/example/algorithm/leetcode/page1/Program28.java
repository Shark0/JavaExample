package com.shark.example.algorithm.leetcode.page1;

public class Program28 {
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
        Program28 strStr = new Program28();
        System.out.println(strStr.strStr("hello", "llo"));
    }
}

