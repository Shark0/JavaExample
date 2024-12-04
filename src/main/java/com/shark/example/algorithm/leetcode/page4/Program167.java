package com.shark.example.algorithm.leetcode.page4;

import java.util.Arrays;

public class Program167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                break;
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Program167().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
