package com.shark.example.algorithm.leetcode.page34;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Program1679 {

    public int maxOperations(int[] nums, int k) {
        int result = 0;
        int start = 0;
        int end = nums.length - 1;
        Arrays.sort(nums);
        while (start < end) {
            int v1 = nums[start];
            int v2 = nums[end];
            int sum = v1 + v2;
            if(sum < k) {
                start++;
            } else if (sum == k) {
                result++;
                start++;
                end--;
            } else {
                end--;
            }

        }
        return result;
    }


    public int maxOperations1(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            int count = numMap.getOrDefault(num, 0);
            numMap.put(num, count + 1);
        }
        for (int num : nums) {
            if (k > num) {
                int numCount = numMap.getOrDefault(num, 0);
                if (numCount == 0) {
                    continue;
                }
                numMap.put(num, numCount - 1);

                int otherNum = k - num;
                if (numMap.containsKey(otherNum)) {
                    int otherNumCount = numMap.get(otherNum);
                    if (otherNumCount > 0) {
                        numMap.put(otherNum, otherNumCount - 1);
                        result++;
                    }
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Program1679 program3679 = new Program1679();
        int[] nums = new int[]{3, 1, 3, 4, 3};
        int k = 6;
        System.out.println(program3679.maxOperations(nums, k));
    }
}
