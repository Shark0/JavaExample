package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Program120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] results = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        for (int[] row : results) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int value = triangle.get(i).get(j);
                if (i == 0) {
                    results[i][j] = value;
                } else {
                    if (j == 0) {
                        results[i][j] = value + results[i - 1][j];
                    } else {
                        int previewResult1 = results[i - 1][j - 1];
                        int previewResult2 = results[i - 1][j];
                        results[i][j] = Math.min(previewResult1, previewResult2) + value;
                    }
                }
            }
        }
        int[] lastRow = results[results.length - 1];
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < results[0].length; i++) {
            if (lastRow[i] < result) {
                result = lastRow[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        System.out.println(new Program120().minimumTotal(triangle));
    }
}
