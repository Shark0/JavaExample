package com.shark.example.algorithm.leetcode.page5;

import java.util.PriorityQueue;

public class Program215 {


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            priorityQueue.add(num);
            if(i >= k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.poll();
    }

    public int findKthLargest1(int[] nums, int k) {
        if (k >= nums.length) {
            k = 1;
        }
        Integer[] results = new Integer[k];
        for (int num : nums) {
            Integer value = num;
            if (results[0] != null && value <= results[0]) {
                continue;
            }
            int index = k - 1;
            while (value != null && index >= 0) {
                Integer temp = results[index];
                if (temp == null || temp < value) {
                    results[index] = value;
                    value = temp;
                }

                index--;
            }
        }
        if (results[0] == null) {
            return -1;
        }
        return results[0];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("result: " + new Program215().findKthLargest(nums, 4));
    }
}
