package com.shark.example.algorithm.leetcode.program11;

public class ContainerWithMostWater {

    public int maxArea(int[] heights) {
        int maxWater = 0;
        int maxStartHeight = 0;
        for(int startIndex = 0; startIndex < heights.length; startIndex++ ) {
            int startHeight = heights[startIndex];
            if(startHeight <= maxStartHeight) {
                continue;
            }
            int maxEndHeight = 0;
            for(int endIndex = (heights.length - 1); endIndex > startIndex; endIndex--) {
                int endHeight = heights[endIndex];
                if(endHeight <= maxEndHeight) {
                    continue;
                }
                int width = endIndex - startIndex;
                int water = width * Math.min(startHeight, endHeight);
                if(water > maxWater) {
                    maxWater = water;
                    maxStartHeight = startHeight;
                    maxEndHeight = endHeight;
                }
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        ContainerWithMostWater container = new ContainerWithMostWater();
        System.out.println(container.maxArea(new int[]{10, 10, 100}));
    }
}
