package com.shark.example.algorithm.leetcode.page4;

public class Program191 {

    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            int temp = n & 1;
            if(temp == 1) {
                result++;
            }
            n = n >>> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Program191().hammingWeight(7));
    }
}
