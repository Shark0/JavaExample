package com.shark.example.algorithm.leetcode.page23;

public class Program1137 {
    public int tribonacci(int n) {
        int tn = 0;
        int tn1 = 1;
        int tn2 = 1;

        if (n == 0) {
            return tn;
        } else if (n == 1) {
            return tn1;
        } else if (n == 2) {
            return tn2;
        } else {
            int result = 0;
            int index = 3;
            while (index <= n) {
                result = tn + tn1 + tn2;
                tn = tn1;
                tn1 = tn2;
                tn2 = result;
                index++;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int n = 25;
        System.out.println("result: " + new Program1137().tribonacci(25));
    }
}
