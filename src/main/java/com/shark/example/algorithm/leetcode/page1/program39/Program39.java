package com.shark.example.algorithm.leetcode.page1.program39;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate <= target) {
                if (candidate == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(candidate);
                    results.add(list);
                } else {
                    int nextTarget = target - candidate;
                    int[] nextCandidates = new int[candidates.length - i];
                    System.arraycopy(candidates, i, nextCandidates, 0, nextCandidates.length);
                    List<List<Integer>> nextResults = combinationSum(nextCandidates, nextTarget);
                    if (!nextResults.isEmpty()) {
                        for (List<Integer> nextResult : nextResults) {
                            nextResult.add(candidate);
                            results.add(nextResult);
                        }
                    }
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Program39 program39 = new Program39();
        List<List<Integer>> result = program39.combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println("result: " + gson.toJson(result));
    }
}
