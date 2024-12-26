package com.shark.example.algorithm.leetcode.page5;

public class Program201 {

    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left = left >> 1;
            right = right >> 1;
            count++;
        }
        return left << count;
    }

    public static void main(String[] args) {
        System.out.println(new Program201().rangeBitwiseAnd(5, 7));
    }
}
