package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Comparator;

public class Program435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        System.out.println("intervals: " + new Gson().toJson(intervals));
        int result = 0;
        int max = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            System.out.println("i: " + i + ", intervals[i][0]: " + intervals[i][0] + ", end: " + max);
            if (intervals[i][0] < max) {
                System.out.println("overlapping");
                result++;
            } else {
                max = intervals[i][1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = {new int[]{1, 100}, new int[]{11, 22}, new int[]{1, 11}, new int[]{2, 12}};
        System.out.println("result: " + new Program435().eraseOverlapIntervals(intervals));
    }
}
