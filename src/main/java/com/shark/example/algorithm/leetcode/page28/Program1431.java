package com.shark.example.algorithm.leetcode.page28;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program1431 {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int j : candies) {
            if (j > max) {
                max = j;
            }
        }
        List<Boolean> results = new ArrayList<>();
        for (int candy : candies) {
            results.add((candy + extraCandies >= max));
        }
        return results;
    }

    public static void main(String[] args) {
        Program1431 program1431 = new Program1431();
        List<Boolean> results = program1431.kidsWithCandies(new int[]{1, 2, 3, 4, 5}, 3);
        System.out.println("results: " + new Gson().toJson(results));
    }
}
