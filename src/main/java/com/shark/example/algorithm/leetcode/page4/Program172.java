package com.shark.example.algorithm.leetcode.page4;

public class Program172 {

    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            n = n / 5;
            res += n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Program172().trailingZeroes(15));
    }
}
