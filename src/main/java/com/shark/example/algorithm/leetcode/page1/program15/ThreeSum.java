package com.shark.example.algorithm.leetcode.page1.program15;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);

        int i = 0;
        while (i < nums.length - 2) {


            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int result = nums[i] + nums[j] + nums[k];
                if (result == 0) {
                    resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (result < 0) {
                    j++;
                } else {
                    k--;
                }
            }

            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i ++;
            }
            i++;
        }
        return resultList;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
    }
}
