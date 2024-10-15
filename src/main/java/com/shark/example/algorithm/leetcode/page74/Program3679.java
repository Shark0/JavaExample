package com.shark.example.algorithm.leetcode.page74;

import java.util.HashMap;
import java.util.Map;

public class Program3679 {

    public int maxOperations(int[] nums, int k) {
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
        Program3679 program3679 = new Program3679();
        int[] nums = new int[]{3, 1, 3, 4, 3};
        int k = 6;
        System.out.println(program3679.maxOperations(nums, k));
    }
}
