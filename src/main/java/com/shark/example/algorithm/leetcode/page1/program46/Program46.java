package com.shark.example.algorithm.leetcode.page1.program46;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = null;
        int index = 0;
        while (index < nums.length) {
            List<List<Integer>> tempResultList = new ArrayList<>();
            if (results == null) {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(nums[index]);
                tempResultList.add(tempList);
            } else {
                int value = nums[index];
                for (List<Integer> list : results) {
                    for (int i = 0; i <= list.size(); i++) {
                        List<Integer> tempList = new ArrayList<>(list);
                        tempList.add(i, value);
                        tempResultList.add(tempList);
                    }
                }
            }
            results = tempResultList;
            index++;
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new Program46().permute(nums);
        System.out.println("result: " + new Gson().toJson(result));
    }
}
