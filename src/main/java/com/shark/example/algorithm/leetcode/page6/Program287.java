package com.shark.example.algorithm.leetcode.page6;

import java.util.HashSet;
import java.util.Set;

public class Program287 {
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    public static void main(String[] argv) {
        int result = new Program287().findDuplicate(new int[]{1,3,4,2,2});
        System.out.println("result: " + result);
    }
}
