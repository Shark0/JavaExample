package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program35 {
    public int searchInsert(int[] nums, int target) {
        if(target <= nums[0]) {
            return 0;
        }
        if(target > nums[nums.length-1]) {
            return nums.length;
        }

        int headIndex = 0;
        int tailIndex = nums.length - 1;
        int index = -1;
        int headValue;
        int tailValue;
        while(headIndex <= tailIndex) {
            headValue = nums[headIndex];
            if(target <= headValue) {
                return headIndex;
            }
            tailValue = nums[tailIndex];
            if(tailValue < target) {
                return tailIndex + 1;
            }

            index = (headIndex + tailIndex) / 2;
            int value = nums[index];

            System.out.println("headIndex = " + headIndex + ", headValue = " + headValue +
                    ", tailIndex = " + tailIndex + ", tailValue = " + tailValue +
                    ", index = " + index + ", value = " + value +
                    ", target = " + target);

            if(value == target) {
                return index;
            }
            if(value < target) {
                headIndex = index + 1;
                tailIndex = tailIndex - 1;
            } else {
                headIndex = headIndex + 1;
                tailIndex = index - 1;
            }
            System.out.println("headIndex = " + headIndex + ", tailIndex = " + tailIndex);
        }
        System.out.println("index = " + index + ", nums[index] = " + nums[index] + ", target = " + target);
        if(target > nums[index]) {
            System.out.println("target > nums[index]");
            index = index + 1;
        }
        return index;
    }

    public static void main(String[] args) {
        Program35 program35 = new Program35();
        int[] nums = new int[]{1,3,5,6};
        int target = 2;
        System.out.println("nums = " + new Gson().toJson(nums) + ", target = " + target);
        int result = program35.searchInsert(nums, target);
        System.out.println("result = " + result);
    }
}
