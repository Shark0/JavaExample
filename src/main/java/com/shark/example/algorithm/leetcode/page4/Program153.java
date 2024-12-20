package com.shark.example.algorithm.leetcode.page4;

public class Program153 {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;

            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(new Program153().findMin(new int[]{3, 4, 5, 1, 2}));
    }
}
