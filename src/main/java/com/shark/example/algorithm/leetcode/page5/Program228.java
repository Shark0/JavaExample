package com.shark.example.algorithm.leetcode.page5;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program228 {

    public List<String> summaryRanges(int[] nums) {
        List<String> results = new ArrayList<>();
        if (nums.length == 0) {
            return results;
        }
        if (nums.length == 1) {
            results.add(String.valueOf(nums[0]));
            return results;
        }

        int start = nums[0];
        int preview = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            if (i == nums.length - 1) {
                if (current == preview + 1) {
                    results.add(start + "->" + current);
                } else {
                    if (start == preview) {
                        results.add(String.valueOf(start));
                    } else {
                        results.add(start + "->" + preview);
                    }
                    results.add(String.valueOf(current));
                }
            } else {

                if (current != preview + 1) {
                    if (start == preview) {
                        results.add(String.valueOf(start));
                    } else {
                        results.add(start + "->" + preview);
                    }
                    start = current;
                }
                preview = current;
            }
        }
        return results;
    }

    public static void main(String[] args) {

        System.out.println(new Gson().toJson(new Program228().summaryRanges(new int[]{1, 2, 3, 4, 5, 7, 8, 10})));
    }
}
