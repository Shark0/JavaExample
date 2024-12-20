package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program26 {
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
        Program26 removeDuplicates = new Program26();
        int[] input =  new int[]{1,1,2};
        System.out.println(removeDuplicates.removeDuplicates(input));
        System.out.println(new Gson().toJson(input));
    }
}
