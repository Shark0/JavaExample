package com.shark.example.algorithm.leetcode.page15;

public class Program724 {

    public int pivotIndex(int[] nums) {
        int left = 0;
        int right = 0;
        for(int num : nums) {
            right = right + num;
        }

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            right = right - num;
            if(left == right) {
                return i;
            }
            left = left + num;
        }

        return -1;
    }

    public static void main(String[] args) {
        Program724 program724 = new Program724();
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        int pivotIndex = program724.pivotIndex(nums);
        System.out.println("pivotIndex: " + pivotIndex);
    }
}
