package com.shark.example.algorithm.leetcode.program16;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int currentSum =  nums[0] + nums[1] + nums[2];
        int currentDifference = Math.abs(target - currentSum);
        if(currentDifference == 0) {
            return target;
        }
        int i = 0;
        while (i < nums.length - 2) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                int difference = Math.abs(target - sum);
                if (difference < currentDifference) {
                    System.out.println("difference = " + difference + ", sum = " + sum);
                    currentSum = sum;
                    currentDifference = difference;
                }
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return currentSum;
    }

    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(new int[]{2, 3, 8, 9, 10}, 16));
    }
}
