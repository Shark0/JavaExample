package com.shark.example.algorithm.leetcode.page2;

public class Program69 {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int start = 1;
        int end = x;
        int mid;

        while (start <= end) {
            mid = start + (end - start) / 2;
            if ((long) mid * mid > (long) x) {
                end = mid - 1;
            } else if (mid * mid == x) {
                return mid;
            } else if ((long) mid * mid < (long) x) {
                start = mid + 1;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        Program69 program69 = new Program69();
        System.out.println(program69.mySqrt(8));
    }
}
