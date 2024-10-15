package com.shark.example.algorithm;

import com.google.gson.Gson;

public class MatrixUtil {
    public static void print(int[][] matrix) {
        Gson gson = new Gson();
        for (int[] row : matrix) {
            String rowJson = gson.toJson(row);
            System.out.println(rowJson);
        }
    }
}
