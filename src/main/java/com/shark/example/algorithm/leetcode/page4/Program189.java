package com.shark.example.algorithm.leetcode.page4;

import com.google.gson.Gson;

public class Program189 {

    public void rotate(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int[] replaceArray = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int replaceIndex = (i + k) % nums.length;
            int nextReplaceNum = nums[replaceIndex];
            if(i < k) {
                nums[replaceIndex] = nums[i];
            } else {
                nums[replaceIndex] = replaceArray[i % k];
            }
            replaceArray[i % k] = nextReplaceNum;
        }
    }

    public static void main(String[] args) {
        Program189 program189 = new Program189();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        program189.rotate(nums, 3);
        System.out.println(new Gson().toJson(nums));
    }

}
