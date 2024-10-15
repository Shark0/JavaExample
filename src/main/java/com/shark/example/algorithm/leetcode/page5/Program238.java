package com.shark.example.algorithm.leetcode.page5;

import com.google.gson.Gson;

public class Program238 {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int totalValue = 1;
        int zeroCount = 0;
        for(int value : nums) {
            if(value != 0) {
                totalValue *= value;
            } else {
                zeroCount++;
            }
        }

        if(zeroCount == 0) {
            for(int i = 0; i < nums.length; i++) {
                result[i] = totalValue / nums[i];
            }
        } else if(zeroCount == 1)  {
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] == 0) {
                    result[i] = totalValue;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Program238 program238 = new Program238();
        int[] result = program238.productExceptSelf(new int[]{1, 2, 3, 4});
        System.out.println("result: " + new Gson().toJson(result));
    }
}
