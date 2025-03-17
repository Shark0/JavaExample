package com.shark.example.algorithm.leetcode.page68;

import java.util.*;

public class Program3356 {

    public int minZeroArray(int[] nums, int[][] queries) {
        int leftIndex = findLeftIndex(nums, 0);
        int rightIndex = findRightIndex(nums, nums.length - 1);
        int step = 0;
        if (leftIndex > rightIndex) {
            return 0;
        }

        for (int[] query : queries) {
            int startIndex = query[0];
            int endIndex = query[1];
            System.out.println("init startIndex: " + startIndex + " endIndex: " + endIndex);
            System.out.println("leftIndex: " + leftIndex + " rightIndex: " + rightIndex);
            if (startIndex > rightIndex) {
                step++;
                continue;
            } else {
                startIndex = Math.max(startIndex, leftIndex);
            }

            if (endIndex < leftIndex) {
                step++;
                continue;
            } else {
                endIndex = Math.min(endIndex, rightIndex);
            }
            System.out.println("final startIndex: " + startIndex + " endIndex: " + endIndex);
            if (startIndex > endIndex) {
                step++;
                continue;
            }
            int value = query[2];
            if(value == 0) {
                step++;
                continue;
            }
            for (int i = startIndex; i <= endIndex; i++) {
                if(nums[i] <= 0) {
                    continue;
                }
                nums[i] = nums[i] - value;
            }
            System.out.println("nums: " + Arrays.toString(nums));
            step++;
            leftIndex = findLeftIndex(nums, leftIndex);
            rightIndex = findRightIndex(nums, rightIndex);
            System.out.println("final leftIndex: " + leftIndex + " rightIndex: " + rightIndex);
            if (leftIndex > rightIndex) {
                return step;
            }
        }
        return -1;
    }

    private int findLeftIndex(int[] nums, int leftIndex) {
        while (nums[leftIndex] <= 0) {
            leftIndex++;
            if (leftIndex >= nums.length - 1) {
                break;
            }
        }
        return leftIndex;
    }

    private int findRightIndex(int[] nums, int rightIndex) {
        while (nums[rightIndex] <= 0) {
            rightIndex--;
            if (rightIndex <= 0) {
                break;
            }
        }
        return rightIndex;
    }

    public static void main(String[] args) {
        //        int[] nums = new int[]{0};

//        int[] nums = new int[]{4, 3, 2, 1};
//        int[][] queries = new int[][]{{1, 3, 2}, {0, 2, 1}};

        int[] nums = new int[]{1, 0, 0, 3, 6, 4, 0, 3, 8, 4, 6, 6, 0, 5, 8, 0, 8};
        int[][] queries = new int[][]{{3, 4, 3}, {12, 14, 2}, {5, 16, 1}, {10, 10, 4}, {12, 14, 5}, {2, 11, 4}, {0, 16, 3}, {0, 14, 4}, {1, 8, 4}, {8, 12, 1}, {8, 10, 1}, {3, 11, 3}, {7, 16, 5}, {12, 12, 4}, {9, 15, 5}};
        System.out.println(new Program3356().minZeroArray(nums, queries));
    }
}
