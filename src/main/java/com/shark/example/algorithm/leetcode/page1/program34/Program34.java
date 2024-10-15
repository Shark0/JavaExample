package com.shark.example.algorithm.leetcode.page1.program34;

import com.google.gson.Gson;

public class Program34 {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }
        int[] result = new int[2];
        int startIndex = -1;
        int endIndex = -1;
        for(int i = 0 ; i < nums.length ; i++) {
            int value = nums[i];
            if(value == target) {
                if(startIndex == -1) {
                    startIndex = i;
                }
                endIndex = i;
            } else {
                if(startIndex != -1) {
                    break;
                }
            }
        }
        result[0] = startIndex;
        result[1] = endIndex;
        return result;
    }

    public static void main(String[] args) {
        Program34 program34 = new Program34();
        int[] result = program34.searchRange(new int[]{1, 3}, 3);
        System.out.println("result: " + new Gson().toJson(result));
    }
}
