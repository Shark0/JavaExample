package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> results = new ArrayList<>();
        if(intervals.length == 0){
            results.add(newInterval);
            return results.toArray(new int[0][]);
        }

        int previewStart = 0;
        int previewEnd = 0;
        boolean hasInsert = false;
        for (int i = 0; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];
            if(i == 0) {
                //handle start and end
                previewStart = currentStart;
                previewEnd = currentEnd;
            } else {
                if(currentStart <= previewEnd) {
                    //overlap
                    if(currentEnd > previewEnd) {
                        previewEnd = currentEnd;
                    }
                } else {
                    //non overlap
                    results.add(new int[]{previewStart, previewEnd});
                    previewStart = currentStart;
                    previewEnd = currentEnd;
                }
            }
            //handle insert
            if(!hasInsert){
                int newIntervalStart = newInterval[0];
                int newIntervalEnd = newInterval[1];
                if(newIntervalEnd < previewStart) {
                    //non overlap and in the front
                    results.add(newInterval);
                    hasInsert = true;
                } else if(newIntervalStart <= previewStart) {
                    //overlap end
                    previewStart = newIntervalStart;
                    if(newIntervalEnd > previewEnd) {
                        previewEnd = newIntervalEnd;
                    }
                    hasInsert = true;
                } else if(newIntervalStart <= previewEnd) {
                    //overlap start
                    if(newIntervalEnd > previewEnd) {
                        previewEnd = newIntervalEnd;
                    }
                    hasInsert = true;
                }
            }
        }
        results.add(new int[]{previewStart, previewEnd});
        if(!hasInsert){
            results.add(newInterval);
        }
        return results.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {4, 6}, {8, 10}, {15, 18}};
        int[] newInterval = new int[]{3, 4};
        System.out.println(new Gson().toJson(new Program57().insert(intervals, newInterval)));

    }
}
