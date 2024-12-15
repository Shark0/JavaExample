package com.shark.example.algorithm.leetcode.page2;

public class Program70 {

    public int climbStairs(int n) {
        int[] results = new int[n];
        results[0] = 1; //1
        results[1] = 2; //[1,1],[2]
        for (int i = 2; i < n; i++) {
            results[i] = results[i - 1] + results[i - 2];
        }
        return results[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Program70().climbStairs(3));
    }
}
