package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;
import com.shark.example.algorithm.MatrixUtil;

public class Program48 {

    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length - i - 1; j++) {
                int temp1 = matrix[i][j];
                int temp2 = matrix[j][matrix.length - i - 1];
                int temp3 = matrix[matrix.length - i - 1][matrix.length - j - 1];
                int temp4 = matrix[matrix.length - j - 1][i];
                System.out.println("i:, " + i + ", j: " + j);
                System.out.println("temp1: " + temp1 + ", temp2: " + temp2 + ", temp3: " + temp3 + ", temp4: " + temp4);
                matrix[i][j] = temp4;
                matrix[j][matrix.length - i - 1] = temp1;
                matrix[matrix.length - i - 1][matrix.length - j - 1] = temp2;
                matrix[matrix.length - j - 1][i] = temp3;
            }
        }
    }

    public void rotateV1(int[][] matrix) {
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
        int[][] matrix = new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        Program48 program48 = new Program48();
        program48.rotate(matrix);
        System.out.println("matrix: " + new Gson().toJson(matrix));
    }
}
