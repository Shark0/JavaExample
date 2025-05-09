package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        for (int count = 0; count <= nums.length; count++) {
            List<List<Integer>> countResults = subsets(nums, count, 0);
            results.addAll(countResults);
        }
        return results;
    }

    private List<List<Integer>> subsets(int[] nums, int count, int offset) {
        List<List<Integer>> results = new ArrayList<>();
        if (count == 0) {
            results.add(new ArrayList<>());
        } else {
            for (int i = 0; (i + count + offset) <= nums.length; i++) {
                List<List<Integer>> subResults = subsets(nums, count - 1, 1 + offset + i);
                int value = nums[i + offset];

                for (List<Integer> subResult : subResults) {
                    subResult.addFirst(value);

                }
//                System.out.println("value: " + value + ", subResults: " + new Gson().toJson(subResults));
                results.addAll(subResults);
            }
        }
//        System.out.println("count: " + count + ", offset: " + offset + ", results: " + new Gson().toJson(results));
        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> results = new Program78().subsets(new int[]{1, 2, 3});
        System.out.println(new Gson().toJson(results));
    }

}
