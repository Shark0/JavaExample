package com.shark.example.algorithm.leetcode.page2;

import java.util.Arrays;

public class Program66 {

    public int[] plusOne(int[] digits) {
        int temp = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i] + temp;
            if (i == digits.length - 1) {
                digit = digit + 1;
            }
            digits[i] = digit % 10;
            temp = digit / 10;
        }
        if (temp == 0) {
            return digits;
        }
        int[] results = new int[digits.length + 1];
        System.arraycopy(digits, 0, results, 1, results.length - 1);
        results[0] = temp;
        return results;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Program66().plusOne(new int[]{9, 9, 9})));
    }

}
