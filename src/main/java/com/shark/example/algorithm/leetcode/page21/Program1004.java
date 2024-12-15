package com.shark.example.algorithm.leetcode.page21;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program1004 {

    public int longestOnes(int[] nums, int k) {

        int max = 0;
        int startIndex = 0;

        List<Integer> zeroIndexList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (value == 0) {
                zeroIndexList.add(i);
                if (zeroIndexList.size() > k) {
                    startIndex = zeroIndexList.get(0) + 1;
                    zeroIndexList.remove(0);
                }
            }
            max = Math.max(max, i - startIndex + 1);
            System.out.println("zeroIndexList: " + new Gson().toJson(zeroIndexList));
            System.out.println("startIndex: " + startIndex + ", i: " + i);
        }
        return max;
    }


    public int longestOnes1(int[] nums, int k) {

        int i = 0;
        int j = 0;
        int currentOneCount = 0;
        int currentZeroCount = 0;
        int max = 0;
        while (i < nums.length) {
            int num = nums[i];
            if (num == 0) {
                currentZeroCount++;
            } else {
                currentOneCount++;
            }
            if (currentZeroCount > k) {
                int previewNum = nums[j];
                if (previewNum == 0) {
                    currentZeroCount--;
                } else {
                    currentOneCount--;
                }
                j++;
            }

            if (currentZeroCount <= k) {
                int tempMax = currentOneCount + currentZeroCount;
                if (tempMax > max) {
                    max = tempMax;
                }
            }

            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        Program1004 p = new Program1004();
        int result = p.longestOnes(input, 2);
        System.out.println("result = " + result);
    }
}
