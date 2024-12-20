package com.shark.example.algorithm.leetcode.page1;

public class Program11 {


    public int maxArea(int[] heights) {
        int area = 0;
        int start = 0;
        int end = heights.length - 1;

        while (start < end) {
            int startHeight = heights[start];
            int endHeight = heights[end];

            int currentArea = Math.min(startHeight, endHeight) * (end - start);
            if (startHeight < endHeight) {
                start++;
            } else {
                end--;
            }
            area = Math.max(area, currentArea);
        }


        return area;
    }

    public int maxArea1(int[] heights) {
        int maxWater = 0;
        int maxStartHeight = 0;
        for (int startIndex = 0; startIndex < heights.length; startIndex++) {
            int startHeight = heights[startIndex];
            if (startHeight <= maxStartHeight) {
                continue;
            }
            int maxEndHeight = 0;
            for (int endIndex = (heights.length - 1); endIndex > startIndex; endIndex--) {
                int endHeight = heights[endIndex];
                if (endHeight <= maxEndHeight) {
                    continue;
                }
                int width = endIndex - startIndex;
                int water = width * Math.min(startHeight, endHeight);
                if (water > maxWater) {
                    maxWater = water;
                    maxStartHeight = startHeight;
                    maxEndHeight = endHeight;
                }
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        Program11 container = new Program11();
        System.out.println(container.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
