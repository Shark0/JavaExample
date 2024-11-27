package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        for(int i = nums1.length  - 1; i >= 0; i--) {
            if(nums1Index >= 0 && nums2Index >= 0) {
                int num1 = nums1[nums1Index];
                int num2 = nums2[nums2Index];
                if(num1 > num2) {
                    nums1[i] = num1;
                    nums1Index--;
                } else {
                    nums1[i] = num2;
                    nums2Index--;
                }
            } else if(nums1Index >= 0) {
                nums1[i] = nums1[nums1Index];
                nums1Index--;
            } else{
                nums1[i] = nums2[nums2Index];
                nums2Index--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        new Program88().merge(nums1, m, nums2, n);
        System.out.println("num1: " + new Gson().toJson(nums1));
    }
}
