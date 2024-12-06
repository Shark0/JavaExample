package com.shark.example.algorithm.leetcode.page5;

import java.util.HashSet;
import java.util.Set;

public class Program219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(i - k > 0) {
                set.remove(nums[i - k - 1]);
            }
            int num = nums[i];
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Program219().containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
    }
}
