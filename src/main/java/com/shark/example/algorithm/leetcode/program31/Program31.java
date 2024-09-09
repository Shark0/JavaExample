package com.shark.example.algorithm.leetcode.program31;

import com.google.gson.Gson;

public class Program31 {

    public void nextPermutation(int[] nums) {
        //find first desc number var1
        int i = nums.length - 2;
        while (i  >= 0) {
            System.out.println("while i: " + i);
            if(nums[i + 1] > nums[i]) {
                break;
            }
            i --;
        }
        System.out.println("i: " + i);
        //find var2 last bigger than var1
        int j = i + 1;
        while (i >= 0 && j < nums.length - 1) {
            System.out.println("j: " + j + ", num: " + nums[j]);
            if(nums[j + 1] <= nums[i]) {
                break;
            }
            j ++;
        }
        System.out.println("j: " + j);
        //swap first desc number va1 and var2 just bigger than var1
        if(i >= 0) {
            swap(nums, i, j);
        }
        System.out.println("swap result: " + new Gson().toJson(nums));
        //reverse i + 1
        reverse(nums, (i + 1));
        //System.out.println("reverse result: " + new Gson().toJson(nums));
    }

    private void reverse(int[] nums, int swapStartIndex) {
        for(int i = 0; i < (nums.length - swapStartIndex) / 2; i++) {
            int temp = nums[i + swapStartIndex];
            nums[i + swapStartIndex] = nums[nums.length - i - 1];;
            nums[nums.length - i - 1] = temp;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 5, 1};
        System.out.println("input: " + new Gson().toJson(input));
        Program31 program = new Program31();
        program.nextPermutation(input);
        System.out.println("result: " + new Gson().toJson(input));
    }
}
