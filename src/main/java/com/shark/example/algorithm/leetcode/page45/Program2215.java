package com.shark.example.algorithm.leetcode.page45;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program2215 {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result1 = new ArrayList<>();
        List<Integer> result2 = new ArrayList<>();
        results.add(result1);
        results.add(result2);
        int index1 = 0, index2 = 0;
        Integer previewAddNum1 = null, previewAddNum2 = null;
        while (index1 < nums1.length || index2 < nums2.length) {
            if (index1 < nums1.length && index2 < nums2.length) {
                int num1 = nums1[index1];
                int num2 = nums2[index2];

                if (num1 > num2) {
                    if (previewAddNum2 == null || num2 != previewAddNum2) {
                        result2.add(num2);
                        previewAddNum2 = num2;
                    }
                    index2++;
                } else if (num1 == num2) {
                    index1++;
                    while (index1 < nums1.length) {
                        int newNum1 = nums1[index1];
                        if(newNum1 != num1) {
                            break;
                        }
                        index1++;
                    }
                    index2++;
                    while (index2 < nums2.length) {
                        int newNum2 = nums2[index2];
                        if(newNum2 != num2) {
                            break;
                        }
                        index2++;
                    }
                } else {
                    //num2 > num1
                    if (previewAddNum1 == null || num1 != previewAddNum1) {
                        result1.add(num1);
                        previewAddNum1 = num1;
                    }
                    index1++;
                }
            } else if (index1 < nums1.length) {
                int num1 = nums1[index1];
                if (previewAddNum1 == null || num1 != previewAddNum1) {
                    result1.add(num1);
                    previewAddNum1 = num1;
                }
                index1++;
            } else {
                // index2 < nums2.length
                int num2 = nums2[index2];
                if (previewAddNum2 == null || num2 != previewAddNum2) {
                    result2.add(num2);
                    previewAddNum2 = num2;
                }
                index2++;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Program2215 program = new Program2215();
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {4, 4, 5, 6, 7, 8, 9,};

        List<List<Integer>> results = program.findDifference(nums1, nums2);
        System.out.println(new Gson().toJson(results));
    }
}
