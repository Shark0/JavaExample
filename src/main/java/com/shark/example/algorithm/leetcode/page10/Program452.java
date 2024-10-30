package com.shark.example.algorithm.leetcode.page10;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Comparator;

public class Program452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[1])); //用最高來排序
        System.out.println("points: " + new Gson().toJson(points));
        int result = 1;
        int currentHeight = points[0][1];
        for (int i = 1; i < points.length; i++) {
            System.out.println("i: " + i + ", points[i][0]: " + points[i][0] + ", end: " + currentHeight);
            if (points[i][0] > currentHeight) {
                currentHeight = points[i][1];
                result++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] points = new int[][]{new int[]{10, 16}, new int[]{2, 8}, new int[]{1, 6}, new int[]{7, 12}};
        System.out.println("result: " + new Program452().findMinArrowShots(points));
    }
}
