package com.shark.example.algorithm.leetcode.page3;

import java.util.HashSet;

public class Program128 {
    public int longestConsecutive(int[] nums) {
        int max = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int count = 1;
            int temp = num;
            while (set.contains(temp + 1)) {
                count++;
                temp++;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Program128().longestConsecutive(new int[]{100, 1, 2, 3, 4, 5}));
    }
}
