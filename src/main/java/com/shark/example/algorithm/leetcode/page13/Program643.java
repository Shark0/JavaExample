package com.shark.example.algorithm.leetcode.page13;

public class Program643 {

    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        double currentSum = 0;
        int kCount = k;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (kCount > 0) {
                currentSum = currentSum + num;
                sum = currentSum;
                kCount--;
            } else {
                int preNum = nums[i - k];
                currentSum = currentSum + num - preNum;
                if (currentSum > sum && kCount == 0) {
                    sum = currentSum;
                }
            }

        }
        return sum / k;
    }

    public static void main(String[] args) {
        Program643 program643 = new Program643();
        System.out.println(program643.findMaxAverage(new int[]{5}, 1));
    }
}
