package com.shark.example.algorithm.leetcode.page1.program7;

public class Program7 {

    public int reverse(int x) {
        boolean isNegative = x < 0;
        x = Math.abs(x);
        System.out.println("x: " + x);
        long result = 0;
        while (x != 0) {
            int pop = x % 10;

            x = x / 10;
            result = result * 10 + pop;
            System.out.println("pop = " + pop + ", result = " + result);
        }
        result = isNegative ? -result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        Program7 program7 = new Program7();
        int result = program7.reverse(1534236469);
        System.out.println(result);
    }
}
