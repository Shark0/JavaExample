package com.shark.example.algorithm.leetcode.page4;

public class Program190 {

    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int temp = n & 1;
            result = (result << 1) | temp;
            n = n >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Program190().reverseBits(0));
    }
}
