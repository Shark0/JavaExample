package com.shark.example.algorithm.leetcode.page1;

public class Program50 {

    public double myPow(double x, int n) {
        if (x == 1) {
            return 1;
        }
        double result = 1;
        if(n < 0) {
            n = -n;
            x = 1 / x;
        }
        while (n != 0) {
            if (n % 2 == 1) {
                result *= x;
            }
            System.out.println("result: " + result + ", x: " + x + ", n: " + n );
            x *= x;
            n >>>= 1;
        }
        return result;
    }

    public double myPow1(double x, int n) {
        if (x == 1) {
            return 1;
        }
        double result = 1;
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                result = result * x;
            }
        } else {
            for (int i = 0; i > n; i--) {
                result = result / x;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Program50 program50 = new Program50();
        double result = program50.myPow(2.0, -3);
        System.out.println("result: " + result);
    }
}
