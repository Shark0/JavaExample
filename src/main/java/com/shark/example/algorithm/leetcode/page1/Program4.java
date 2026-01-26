package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program4 {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int[] nums = merge(nums1, nums2);
        System.out.println(new Gson().toJson(nums));
        if((nums1.length + nums2.length) % 2 == 0) {
            return (double) (nums[nums.length - 1] + nums[nums.length - 2]) / 2;
        } else {
            return nums[nums.length - 1];
        }
    }

    private int[] merge(int[] nums1, int[] nums2) {

        int numLength;
        numLength = (nums1.length + nums2.length) / 2 + 1;
        System.out.println("numLength: " + numLength);
        int[] nums = new int[numLength];

        int num1Index = 0;
        int num2Index = 0;
        int numIndex = 0;
        while (num1Index < nums1.length && num1Index < numLength &&
                num2Index < nums2.length && num2Index < numLength &&
                numIndex < numLength
        ) {
            int num1 = nums1[num1Index];
            int num2 = nums2[num2Index];
            if(num1 <= num2) {
                nums[numIndex] = num1;
                num1Index ++;
            } else {
                nums[numIndex] = num2;
                num2Index ++;
            }
            numIndex ++;
        }
        if(numIndex == numLength) {
            return nums;
        }

        if(num1Index == nums1.length) {
            while (numIndex < numLength) {
                nums[numIndex] = nums2[num2Index];
                num2Index ++;
                numIndex ++;
            }
        } else {
            while (numIndex < numLength) {
                nums[numIndex] = nums1[num1Index];
                num1Index ++;
                numIndex ++;
            }
        }
        return nums;
    }


    public double findMedianSortedArraysVV1(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length == 0) {
            return 0;
        }
        boolean isEven = length % 2 == 0;
        int maxIndex = length / 2;
        int index = 0;
        int preValue = -1;
        int value = -1;
        int nums1Index = 0;
        int nums2Index = 0;
        while (index <= maxIndex) {
            preValue = value;
            boolean hasNum1 = nums1Index < nums1.length && nums1.length > 0;
            boolean hasNum2 = nums2Index < nums2.length && nums2.length > 0;
            if (hasNum1 && hasNum2) {
                if (nums1[nums1Index] <= nums2[nums2Index]) {
                    value = nums1[nums1Index];
                    nums1Index ++;
                } else {
                    value = nums2[nums2Index];
                    nums2Index ++;
                }
            } else if(hasNum1){
                value = nums1[nums1Index];
                nums1Index ++;
            } else if(hasNum2){
                value = nums2[nums2Index];
                nums2Index ++;
            }
            index++;
            System.out.println("nums1Index = " + nums1Index + ", nums2Index = " + nums2Index);
        }
        if (isEven) {
            return (double) (value + preValue) / 2;
        } else {
            return value;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {3,4};
        int[] nums2 = {};
        Program4 program4 = new Program4();
        double result = program4.findMedianSortedArrays(nums1, nums2);
        System.out.println("result: " + result);
    }
}
