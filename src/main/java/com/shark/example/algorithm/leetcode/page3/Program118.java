package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> previewResult = List.of();
        for(int i = 0; i < numRows; i++) {
            List result = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) {
                    result.add(1);
                } else {
                    result.add(previewResult.get(j - 1) + previewResult.get(j));
                }
            }
            results.add(result);
            previewResult = result;

        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new Program118().generate(5)));
    }
}
