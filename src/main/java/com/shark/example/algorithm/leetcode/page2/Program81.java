package com.shark.example.algorithm.leetcode.page2;

public class Program81 {

    public boolean search(int[] nums, int target) {
        int mid = nums.length / 2;
        for(int i = 0; i < (nums.length / 2 + 1); i++) {
            int head = mid - i;
            if(head >= 0 && nums[head] == target) {
                return true;
            }
            int tail = mid + i;
            if(tail < nums.length && nums[tail] == target) {
                return true;
            }
        }
        return false;
    }
}
