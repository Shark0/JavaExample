package com.shark.example.algorithm.leetcode.page1;

import java.util.HashSet;
import java.util.Set;

public class Program41 {


    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int value : nums) {
            if (value > 0) {
                set.add(value);
            }
        }

        int value = 1;
        while (value <= nums.length + 1) {
            if(!set.contains(value)) {
                return value;
            }
            value ++;
        }
        return -1;
    }


    public static void main(String[] args) {
        Program41 program41 = new Program41();
        int result = program41.firstMissingPositive(new int[]{1, 2, 0});
        System.out.println("First positive is: " + result);
    }
}
