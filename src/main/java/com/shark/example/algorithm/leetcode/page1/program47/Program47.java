package com.shark.example.algorithm.leetcode.page1.program47;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Program47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = null;
        int index = 0;
        Set<String> set = new HashSet<>();
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
                        if (i < list.size() && list.get(i) == value) {
                            continue;
                        }
                        List<Integer> tempResult = new ArrayList<>(list);

                        tempResult.add(i, value);
                        String key = tempResult.toString();
                        if(set.contains(key)) {
                            continue;
                        }
                        tempResultList.add(tempResult);
                        set.add(key);
                    }
                }
            }

            results = tempResultList;
            index++;
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1};
        List<List<Integer>> result = new Program47().permuteUnique(nums);
        System.out.println("result: " + new Gson().toJson(result));
    }
}
