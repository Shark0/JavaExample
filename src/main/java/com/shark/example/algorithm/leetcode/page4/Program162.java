package com.shark.example.algorithm.leetcode.page4;

public class Program162 {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int preview;
            if (i - 1 < 0) {
                preview = Integer.MIN_VALUE;
            } else {
                preview = nums[i - 1];
            }
            int next;
            if(i + 1 == nums.length) {
                next = Integer.MIN_VALUE;
            } else {
                next = nums[i + 1];
            }
            if (current > preview && current > next) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println("result: " + new Program162().findPeakElement(nums));
    }
}
