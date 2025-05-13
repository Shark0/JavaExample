package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int end = start + 1; end <= nums.length ; end++) {
            System.out.println("start: " + start + ", end: " + end + ", path: " + new Gson().toJson(path) + ", n: " + nums[end - 1]);
            path.add(nums[end - 1]);

            backtrack(nums, end, path, result);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> results = new Program78().subsets(new int[]{0, 1, 2});
        System.out.println(new Gson().toJson(results));
    }

}
