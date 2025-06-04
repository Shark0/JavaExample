package com.shark.example.algorithm.leetcode.page2;

public class Program84 {

    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            if (i > 0 && heights[i - 1] >= heights[i]) {
                continue;
            }
//            System.out.println("i: " + i + ", heights[i]: " + heights[i] + ", max: " + max);

            int minHeight = heights[i];
            for (int j = i; j < heights.length; j++) {
                int height = heights[j];
                if(height < minHeight) {
                    minHeight = height;
                    if(minHeight * (heights.length - i) < max) {
                        break;
                    }
                }
                int width = j - i + 1;
                int temp = width * minHeight;
                if(temp > max) {
                    max = temp;
                }
//                System.out.println("j: " + j + ", width: " + width + ", minHeight: " + minHeight + ", temp: " + temp);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] heights = new int[]{3, 6, 5, 7, 4, 8, 1, 0};
        System.out.println("result: " + new Program84().largestRectangleArea(heights));
    }
}
