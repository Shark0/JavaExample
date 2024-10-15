package com.shark.example.algorithm.leetcode.page35;

public class Program1732 {

    public int largestAltitude(int[] gain) {
        int height = 0;
        int currentHeight = 0;
        for (int num : gain) {
            currentHeight += num;
            if (currentHeight > height) {
                height = currentHeight;
            }
        }
        return height;
    }

    public static void main(String[] args) {
        Program1732 program1732 = new Program1732();
        int[] gain = new int[]{-5, 1, 5, 0, -7};
        int result = program1732.largestAltitude(gain);
        System.out.println("result = " + result);
    }
}
