package com.shark.example.algorithm.leetcode.page4;

import java.util.Arrays;

public class Program198 {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] robRecordDp = new int[nums.length];
        Arrays.fill(robRecordDp, -1);
        int rob0 = robMax(0, nums, robRecordDp);
        int rob1 = robMax(1, nums, robRecordDp);
        return Math.max(rob0, rob1);
    }

    public int robMax(int index, int[] nums, int[] robRecordDp) {
        int rob = robRecordDp[index];
        if(rob != -1) {
            return rob;
        }
        rob = nums[index];
        if(index < nums.length - 2) {
            int rob1 = Integer.MIN_VALUE;
            if(index + 2 < nums.length) {
                rob1 = robMax(index + 2, nums, robRecordDp);
            }
            int rob2 = Integer.MIN_VALUE;
            if(index + 3 < nums.length) {
                rob2 = robMax(index + 3, nums, robRecordDp);
            }
            rob = rob + Math.max(rob1, rob2);
        }

        robRecordDp[index] = rob;
        System.out.println("index: " + index + ", rob: " + rob);
        return rob;
    }

    public static void main(String[] args) {
        System.out.println("result: " + new Program198().rob(new int[]{1, 2, 3, 1}));
    }
}
