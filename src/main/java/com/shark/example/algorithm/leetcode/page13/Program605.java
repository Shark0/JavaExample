package com.shark.example.algorithm.leetcode.page13;

public class Program605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) {
            return true;
        }
        int i = 0;
        while (i < flowerbed.length - 1) {
            if (flowerbed[i] == 0) {
                if (flowerbed[i + 1] == 0) {
                    n--;
                    if (n == 0) {
                        return true;
                    }
                    flowerbed[i] = 1;
                    i = i + 2;
                } else {
                    i = i + 3;
                }
            } else {
                i = i + 2;
            }
        }
        //check last point
        if (flowerbed.length == 1) {
            if (flowerbed[flowerbed.length - 1] == 0) {
                n--;
            }
        } else if (flowerbed.length > 1) {
            if (flowerbed[flowerbed.length - 1] == 0 &&
                    flowerbed[flowerbed.length - 2] == 0) {
                n--;
            }
        }
        System.out.println("n: " + n);
        return n == 0;
    }

    public static void main(String[] args) {
        Program605 program605 = new Program605();
        boolean result = program605.canPlaceFlowers(new int[]{0,0,0,0,0,1,0,0}, 0);
        System.out.println("result: " + result);
    }
}
