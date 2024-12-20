package com.shark.example.algorithm.leetcode.page1;

public class Program29 {
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        long result = 0;
        if (divisor == -1) {
            result = dividend;
            result = result * -1;
        } else {
            if (dividend < 0) {
                if (divisor < 0) {
                    while (dividend <= divisor) {
                        result = result + 1;
                        dividend = dividend - divisor;
                    }
                } else {
                    while (dividend + divisor <= 0) {
                        result = result - 1;
                        dividend = dividend + divisor;
                    }
                }
            } else {
                if (divisor < 0) {

                    while (dividend + divisor >= 0) {
                        result = result - 1;
                        dividend = dividend + divisor;
                    }
                } else {
                    while (dividend >= divisor) {
                        result = result + 1;
                        dividend = dividend - divisor;
                    }
                }
            }
        }

        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

    public int divide2(int dividend, int divisor) {
        long absResult = 0;
        long sign = 1;
        if (divisor == 1) {
            return dividend;
        }
        if ((dividend < 0 && divisor >= 0) || (dividend >= 0 && divisor < 0)) {
            sign = -1;
        }
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        for (int i = 30; i >= 0; i--) {
            if (absDividend >= (absDivisor << i)) {
                absResult += (1L << i);
                absDividend -= (absDivisor << i);
            }
        }
        return (int) (absResult * sign);
    }

    public static void main(String[] args) {
        Program29 divideTwoIntegers = new Program29();
//        System.out.println(divideTwoIntegers.divide(-2147483648, -1));
        System.out.println(divideTwoIntegers.divide2(-2147483648, 2));
    }
}
