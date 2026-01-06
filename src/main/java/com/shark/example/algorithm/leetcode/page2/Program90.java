package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Program90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> numList = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        results.add(result);
        sub(result, numList, results);
        return results;
    }

    private void sub(List<Integer> previewResult, List<Integer> nums, List<List<Integer>> results) {
        Integer previewNum = null;
        for(int i = 0; i < nums.size(); i ++) {
            Integer currentNum = nums.get(i);
            if(currentNum.equals(previewNum)) {
                continue;
            }
            previewNum = currentNum;
            List<Integer> result = new ArrayList<>(previewResult);
            result.add(currentNum);
            results.add(result);
            if(i + 1 < nums.size()) {
                List<Integer> newNumList = nums.subList(i + 1, nums.size());
                sub(result, newNumList, results);
            }
        }
    }

    public static void main(String[] argv) {
        int[] nums = new int[]{1, 2, 3};
        Program90 program90 = new Program90();
        System.out.println(new Gson().toJson(program90.subsetsWithDup(nums)));
    }
}
