package com.shark.example.algorithm.leetcode.page1;

public class Program5 {

    public static void main(String[] argv) {
        String s = "cbbd";
        System.out.println(new Program5().longestPalindrome(s));
    }


    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int maxLen = 1;
        String resulut = s.substring(0, 1);

        for (int start = 0; start < chars.length; start++) {
            for (int end = start + maxLen; end < s.length(); end++) {
                if (end - start + 1 > maxLen && isPalindrome(start, end, chars)) {
                    maxLen = end - start + 1;
                    resulut = s.substring(start, end + 1);
                }
            }
        }
        return resulut;
    }

    private boolean isPalindrome(int start, int end, char[] chars) {
        for (int i = start; i < (start + end + 1) / 2; i++) {
            if (chars[i] != chars[(start + end) - i]) {
                return false;
            }
        }
        return true;
    }

    public String longestPalindromeV1(String s) {
        String temp = "";
        if (s.length() > 1000) {
            return "";
        }
        char[] chars = s.toCharArray();
        System.out.printf("longestPalindrome chars.length = %d%n", chars.length);
        for (int start = 0; start < chars.length; start++) {
            int end = start;
            while (end < chars.length) {
//                System.out.println(String.format("longestPalindrome start = %d, end = %d", start, end));
                if (isPalindrome(start, end, chars)) {
                    if ((end + 1 - start) > temp.length()) {
//                        System.out.println(String.format("longestPalindrome.isPalindrome start = %d, end = %d", start, end));
                        temp = s.substring(start, end + 1);
                    }
                }
                end++;
            }
            if ((temp.length() + start) == chars.length) {
                break;
            }
        }

        return temp;
    }


}
