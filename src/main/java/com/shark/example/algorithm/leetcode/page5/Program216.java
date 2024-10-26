package com.shark.example.algorithm.leetcode.page5;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program216 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        return backTracking(k, n, 1);
    }

    private List<List<Integer>> backTracking(int resultSize, int sum, int min) {
        System.out.println("resultSize: " + resultSize + ", sum: " + sum + ", min: " + min);
        List<List<Integer>> results = new ArrayList<>();
        if (resultSize == 1) {
            if (sum <= 9) {
                List<Integer> result = new ArrayList<>();
                result.add(sum);
                results.add(result);
            }
            return results;
        }

        int maxValue1 = sum / resultSize + 1;
        if (maxValue1 >= 9) {
            maxValue1 = 9;
        }
        System.out.println("min: " + min + ", max: " + maxValue1);
        if (maxValue1 < 1) {
            return results;
        }

        for (int currentValue = min; currentValue <= maxValue1; currentValue++) {
            int nextResultSize = resultSize - 1;
            int nextSum = sum - currentValue;
            int nextMin = currentValue + 1;
            if (nextSum >= nextMin) {
                System.out.println("nextResultSize: " + nextResultSize + ", nextSum: " + nextSum +
                        ", currentValue: " + currentValue + ", nextMin: " + nextMin);
                List<List<Integer>> subResults = backTracking(nextResultSize, nextSum, nextMin);
                for (List<Integer> subResult : subResults) {
                    List<Integer> result = new ArrayList<>(subResult);
                    result.add(0, currentValue);
                    results.add(result);
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        int k = 2;
        int n = 18;

        System.out.println("result: " + new Gson().toJson(new Program216().combinationSum3(k, n)));
    }
}
