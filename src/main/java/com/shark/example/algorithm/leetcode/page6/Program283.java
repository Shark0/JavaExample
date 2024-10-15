package com.shark.example.algorithm.leetcode.page6;

import com.google.gson.Gson;

public class Program283 {

    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == 0) {
                if (zeroIndex == -1) {
                    zeroIndex = i;
                }
            } else {
                if(zeroIndex != -1) {
                    nums[zeroIndex] = value;
                    nums[i] = 0;
                    zeroIndex ++;
                }
            }
        }
    }

    public void moveZeroes1(int[] nums) {

        int zeroCount = 0;
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == 0) {
                if (zeroCount == 0) {
                    zeroIndex = i;
                }
                zeroCount++;
            } else {
                if(zeroCount != 0) {
                    nums[zeroIndex] = value;
                    nums[i] = 0;
                    if(zeroCount > 1) {
                        zeroIndex ++;
                    } else {
                        zeroIndex = i;
                    }
//                    System.out.println("nums: " + new Gson().toJson(nums));
//                    System.out.println("zeroIndex: " + zeroIndex + ", zeroCount: " + zeroCount);
                }
            }
//            System.out.println("value: " + value + ", zeroIndex: " + zeroIndex + ", zeroCount: " + zeroCount);
        }
    }

    public static void main(String[] args) {
        Program283 program283 = new Program283();
        int[] inputs = new int[]{0, 1, 0, 3, 12};
        program283.moveZeroes(inputs);
        System.out.println(new Gson().toJson(inputs));
    }
}
