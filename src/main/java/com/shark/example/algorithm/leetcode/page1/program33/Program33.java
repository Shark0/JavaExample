package com.shark.example.algorithm.leetcode.page1.program33;

import com.google.gson.Gson;

public class Program33 {

    public int search(int[] nums, int target) {
        int headIndex = 0;
        int tailIndex = nums.length - 1;

        while (headIndex <= tailIndex) {
            int headValue = nums[headIndex];
            if (headValue == target) {
                return headIndex;
            }
            int tailValue = nums[tailIndex];
            if (tailValue == target) {
                return tailIndex;
            }

            int index = (headIndex + tailIndex) / 2;
            int value = nums[index];
            if (value == target) {
                return index;
            }
            System.out.println("headIndex = " + headIndex + ", headValue = " + headValue +
                    ", tailIndex = " + tailIndex + ", tailValue = " + tailValue +
                    ", target = " + target + ", value = " + value);
            if (target > value) {
                if(target > tailValue && tailValue > value) {
                    headIndex = headIndex + 1;
                    tailIndex = index - 1;
                } else {
                    headIndex = index + 1;
                    tailIndex = tailIndex - 1;
                }
            } else {
                if(target < headValue && headValue < value) {
                    headIndex = index + 1;
                    tailIndex = tailIndex - 1;
                } else {
                    headIndex = headIndex + 1;
                    tailIndex = index - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Program33 program33 = new Program33();
        int[] nums = new int[]{4,5,6,7,8,1,2,3};
        int target = 8;
        System.out.println("nums: " + new Gson().toJson(nums) + ", target: " + target);
        System.out.println("result: " + program33.search(nums, target));
    }
}
