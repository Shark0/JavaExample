package com.shark.example.algorithm.leetcode.page4;

public class Program152 {

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int preview = 1;
            for (int j = i; j < nums.length; j++) {
                preview = preview * nums[j];
                max = Math.max(max, preview);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Program152().maxProduct(new int[]{2,3,-2,4}));
    }
}
