package com.shark.example.algorithm.leetcode.page1;

import java.util.*;

public class Program15 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);

        int end = nums.length - 1;
        while (end >= 2) {

            int endNum = nums[end];
            int start = 0;
            int middle = end - 1;

            while (start < middle) {
                int middleNum = nums[middle];
                int startNum = nums[start];
                int num = startNum + middleNum + endNum;
                if(num == 0) {
                    resultList.add(Arrays.asList(startNum, middleNum, endNum));
                }
                if (num > 0) {
                    do {
                        middle--;
                    } while (middleNum == nums[middle] && middle > start);
                } else {
                    do {
                        start++;
                    } while (startNum == nums[start] && middle > start);
                }
            }

            do {
                end = end - 1;
            } while (end >= 2 && nums[end] == endNum && nums[end--] == nums[end]);
        }
        return resultList;
    }

    public static void main(String[] args) {
        Program15 threeSum = new Program15();
        System.out.println(threeSum.threeSum(new int[]{0, 0, 0, 0}));
    }
}
