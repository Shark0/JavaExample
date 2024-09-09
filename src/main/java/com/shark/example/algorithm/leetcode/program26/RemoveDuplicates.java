package com.shark.example.algorithm.leetcode.program26;

import com.google.gson.Gson;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int count = 0;
        Integer compareValue = null;
        int flag = 0;
        int arrayLength = nums.length;
        for(int i = 0; i < arrayLength; i++ ) {
            int value = nums[i];
            if(compareValue == null || compareValue < value) {
                compareValue = value;
                nums[flag] = value;
                count++;
                flag++;
            }
        }

        for(int i = 0; i < (arrayLength - count); i ++) {
            nums[arrayLength - i - 1] = -1;
        }
        return count;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] input =  new int[]{1,1,2};
        System.out.println(removeDuplicates.removeDuplicates(input));
        System.out.println(new Gson().toJson(input));
    }
}
