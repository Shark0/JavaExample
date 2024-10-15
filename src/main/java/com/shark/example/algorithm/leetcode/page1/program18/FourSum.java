package com.shark.example.algorithm.leetcode.page1.program18;

import java.util.*;

public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int a = 0;
        while (a < nums.length - 3) {
            int d = nums.length - 1;
            while (d > a + 2) {

                int b = a + 1;
                int c = d - 1;
                while (c > b) {
                    long sum = (long) nums[a] + nums[b] + nums[c] + nums[d];
                    System.out.println("target: " + target);
                    System.out.println("sum = " + sum + ", nums[a] = " + nums[a] + ", nums[b] = " + nums[b] +
                            ", nums[c] = " + nums[c] + ", nums[d] = " + nums[d]);
                    if (sum == target) {
                        List<Integer> list = List.of(nums[a], nums[b], nums[c], nums[d]);
                        result.add(list);
                    }

                    if (sum < target) {
                        while (c > b && nums[b] == nums[b + 1]) {
                            b++;
                        }
                        b++;
                    } else {
                        while (c > b && nums[c] == nums[c - 1]) {
                            c--;
                        }
                        c--;
                    }
                }

                while (d > a + 2 && nums[d] == nums[d - 1]) {
                    d--;
                }
                d--;
            }

            while (a + 1 < nums.length - 3 && nums[a] == nums[a + 1]) {
                a++;
            }
            a++;
        }

        return result;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        List<List<Integer>> list = fourSum.fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);
        System.out.println(list);
    }
}
