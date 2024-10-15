package com.shark.example.algorithm.leetcode.page1.program27;

import com.google.gson.Gson;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int flag = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value != val) {
                nums[flag] = value;
                flag++;
                count++;
            }
        }
        int length = nums.length;
        for (int i = 0; i < (length - count); i++) {
            nums[length - i - 1] = -1;
        }
        return count;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int[] input =  new int[]{1,2,3,4,5,6,7};
        System.out.println(removeElement.removeElement(input, 3));
        System.out.println(new Gson().toJson(input));
    }
}
