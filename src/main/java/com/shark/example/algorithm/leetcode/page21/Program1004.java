package com.shark.example.algorithm.leetcode.page21;

public class Program1004 {

    public int longestOnes(int[] nums, int k) {

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
        int[] input = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        Program1004 p = new Program1004();
        int result = p.longestOnes(input, 3);
        System.out.println("result = " + result);
    }
}
