package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program77 {
    public List<List<Integer>> combine(int n, int k) {
        if(k > n) {
            return List.of();
        }
        List<List<Integer>> results = null;
        int count = 0;

        while (count < k) {
            List<List<Integer>> newResults = new ArrayList<>();
            if(results == null) {
                for (int i = 1; i <= (n - k + 1) ; i++) {
                    List<Integer> result = List.of(i);
                    newResults.add(result);
                }
            } else {
                for(List<Integer> result : results) {
                    int start = result.get(result.size() - 1) + 1;
                    int end = n - k + count + 1;
//                    System.out.println("start: " + start + " end: " + end);
                    for (int i = start; i <= end ; i++) {
                        List<Integer> newResult = new ArrayList<>(result);
                        newResults.add(newResult);
                        newResult.add(i);
                    }
                }
            }
            results = newResults;
            count++;
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new Program77().combine(3, 3)));
    }

}
