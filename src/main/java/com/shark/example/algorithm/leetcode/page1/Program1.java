package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program1 {

    public int[] twoSum(int[] nums, int target) {
        int firstIndex = 0;
        int secondIndex = 0;
        for(int i = 0; i < nums.length - 1; i++){
            firstIndex = i;
            if(nums[firstIndex] > target) {
                continue;
            }
            for(int j = i + 1; j < nums.length; j++){
                secondIndex = j;
                if(nums[secondIndex] > target) {
                    continue;
                }
                if(nums[firstIndex] + nums[secondIndex] == target) {
                    return new int[]{firstIndex, secondIndex};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Program1 twoSum = new Program1();
        System.out.println(new Gson().toJson(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
