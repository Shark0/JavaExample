package com.shark.example.algorithm.leetcode.page29;

public class Program1493 {
    public int longestSubarray(int[] nums) {
        int i = 0;
        int j = 0;
        int zeroCount = 0;
        int oneCount = 0;
        int max = 0;
        while (i < nums.length) {
            int num = nums[i];
            if (num == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
            while (zeroCount > 1) {
                int previewNum = nums[j];
                if (previewNum == 0) {
                    zeroCount--;
                } else {
                    oneCount--;
                }
                j ++;
            }

            int tempMax = zeroCount + oneCount - 1;
            System.out.println("i = " + i + ", j = " + j + ", tempMax = " + tempMax + ", zeroCount = " + zeroCount + ", oneCount = " + oneCount);
            if(tempMax > max) {
                max = tempMax;
            }
            i++;
        }
        return max;
    }

    public static void main(String[] args) {
        Program1493 program1493 = new Program1493();
        int[] input = new int[]{1, 0, 1, 1, 1, 1, 0, 0, 1};
        int result = program1493.longestSubarray(input);
        System.out.println("result: " + result);
    }
}
