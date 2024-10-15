package com.shark.example.algorithm.leetcode.page1.program4;

public class Program4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
        int[] nums1 = {0, 0};
        int[] nums2 = {0, 0};
        Program4 program4 = new Program4();
        double result = program4.findMedianSortedArrays(nums1, nums2);
        System.out.println("result: " + result);
    }
}
