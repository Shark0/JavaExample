package com.shark.example.algorithm.leetcode.page1.program40;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> results = new ArrayList<>();
        int i = 0;
        int previewCandidate = 0;
        while (i < candidates.length) {
            int candidate = candidates[i];
            if(candidate != previewCandidate) {
                previewCandidate = candidate;
                if (candidate <= target) {
                    if (candidate == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(candidate);
                        results.add(list);
                    } else {
                        if(candidates.length - i >= 2) {
                            int nextTarget = target - candidate;
                            int[] nextCandidates = new int[candidates.length - i - 1];
                            System.arraycopy(candidates, i + 1, nextCandidates, 0, nextCandidates.length);
                            List<List<Integer>> nextResults = combinationSum2(nextCandidates, nextTarget);
                            if (!nextResults.isEmpty()) {
                                for (List<Integer> nextResult : nextResults) {
                                    nextResult.add(0, candidate);
                                    results.add(nextResult);
                                }
                            }
                        }
                    }
                }
            }
            i ++;
        }
        return results;
    }

    public static void main(String[] args) {
        Program40 program40 = new Program40();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> results = program40.combinationSum2(candidates, target);
        Gson gson = new Gson();
        System.out.println("result: " + gson.toJson(results));
    }
}
