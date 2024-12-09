package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Program56 {

    public int[][] merge(int[][] intervals) {
        List<int[]> results = new ArrayList<>();
        if(intervals.length == 0 || intervals.length == 1){
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            if(currentStart <= start){
                start = currentStart;
                if (end <= currentEnd) {
                    end = currentEnd;
                }
            } else if (currentStart <= end) {
                if (end <= currentEnd) {
                    end = currentEnd;
                }
            }else {
                results.add(new int[]{start, end});
                start = currentStart;
                end = currentEnd;
            }
        }
        results.add(new int[]{start, end});
        return results.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(new Gson().toJson(new Program56().merge(intervals)));

    }
}
