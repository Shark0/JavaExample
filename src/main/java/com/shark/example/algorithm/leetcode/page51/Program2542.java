package com.shark.example.algorithm.leetcode.page51;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Program2542 {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[]{nums1[i], nums2[i]};
        }
        System.out.println("nums: " + new Gson().toJson(nums));
        Arrays.sort(nums, (a, b) -> b[1] - a[1]);
        System.out.println("nums after sort: " + new Gson().toJson(nums));
        PriorityQueue<Integer> num1PriorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        long result = 0;
        long num1Sum = 0;;
        for (int[] num : nums) {
            num1PriorityQueue.add(num[0]);
            System.out.println("priorityQueue: " + new Gson().toJson(num1PriorityQueue) +
                    ", num: " + new Gson().toJson(num) + ", num[0]: " + num[0]);

            num1Sum = (num1Sum + num[0]);
            if (num1PriorityQueue.size() > k) {
                int minNum1 = num1PriorityQueue.poll();
                System.out.println("minNum2: " + minNum1);
                num1Sum = num1Sum - minNum1;

            }
            System.out.println("num1Sum: " + num1Sum);
            if (num1PriorityQueue.size() == k) {
                result = Math.max(result, (num1Sum * num[1]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 3, 3, 2};
        int[] num2 = new int[]{2, 1, 3, 4};
        int k = 3;
        Program2542 program = new Program2542();
        System.out.println("result = " + program.maxScore(num1, num2, k));

    }
}
