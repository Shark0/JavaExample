package com.shark.example.algorithm.leetcode.page19;

public class Program918 {

    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        int currentMaxSum = 0;
        int currentMinSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;

        for (int num : nums) {
            totalSum = totalSum + num;
            currentMaxSum = Math.max(currentMaxSum + num, num);
            currentMinSum = Math.min(currentMinSum + num, num);
            maxSum = Math.max(maxSum, currentMaxSum);
            minSum = Math.min(minSum, currentMinSum);
        }
        return (maxSum < 0) ? maxSum : Math.max(maxSum, (totalSum - minSum));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, -3, 5};
        System.out.println(new Program918().maxSubarraySumCircular(nums));
    }
}
