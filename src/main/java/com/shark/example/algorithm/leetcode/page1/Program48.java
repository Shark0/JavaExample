package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;
import com.shark.example.algorithm.MatrixUtil;

public class Program48 {
    public void rotate(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[j][matrix.length - i - 1] = matrix[i][j];
            }
        }
        MatrixUtil.print(temp);
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(temp[i], 0, matrix[i], 0, matrix.length);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Program48 program48 = new Program48();
        program48.rotate(matrix);
        System.out.println("matrix: " + new Gson().toJson(matrix));
    }
}
