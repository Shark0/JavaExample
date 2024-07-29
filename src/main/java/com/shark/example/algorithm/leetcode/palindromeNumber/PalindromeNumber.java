package com.shark.example.algorithm.leetcode.palindromeNumber;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int length = str.length();
        for (int i = 0; i < (length / 2); i++) {
            int c1 = str.charAt(i);
            int c2 = str.charAt(length - i - 1);
            if(c1 != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        System.out.println(palindromeNumber.isPalindrome(10));
    }
}
