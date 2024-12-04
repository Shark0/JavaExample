package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program150 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int[][] matrixWall = new int[matrix.length][matrix[0].length];
        int i = 0;
        int j = 0;
        int action = 0; //0: right, 1: down, 2: left, 3: up
        while (true) {
            if (action == 0) {
                while (true) {
                    matrixWall[i][j] = 1;
                    result.add(matrix[i][j]);
//                    System.out.println("action = " + action + ", i= " + i + ", j = " + j);
                    if (j + 1 < matrix[0].length && matrixWall[i][j + 1] != 1) {
                        j++;
                    } else {
                        break;
                    }
                }
                i++;
                if (i >= matrix.length || matrixWall[i][j] == 1) {
                    break;
                }
                action = 1;
            } else if (action == 1) {
                while (true) {
                    matrixWall[i][j] = 1;
                    result.add(matrix[i][j]);
//                    System.out.println("action = " + action + ", i= " + i + ", j = " + j);
                    if (i + 1 < matrix.length && matrixWall[i + 1][j] != 1) {
                        i++;
                    } else {
                        break;
                    }
                }
                j--;
                if (j < 0 || matrixWall[i][j] == 1) {
                    break;
                }
                action = 2;
            } else if (action == 2) {
                while (true) {
                    matrixWall[i][j] = 1;
                    result.add(matrix[i][j]);
//                    System.out.println("action = " + action + ", i= " + i + ", j = " + j);
                    if (j - 1 >= 0 && matrixWall[i][j - 1] != 1) {
                        j--;
                    } else {
                        break;
                    }
                }
                i--;
                if (matrixWall[i][j] == 1) {
                    break;
                }
                action = 3;
            } else {
                while (true) {
                    matrixWall[i][j] = 1;
                    result.add(matrix[i][j]);
//                    System.out.println("action = " + action + ", i= " + i + ", j = " + j);
                    if (i - 1 >= 0 && matrixWall[i - 1][j] != 1) {
                        i--;
                    } else {
                        break;
                    }
                }
                j++;
                if (matrixWall[i][j] == 1) {
                    break;
                }
                action = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3}, {2}};
        System.out.println(new Gson().toJson(new Program150().spiralOrder(matrix)));
    }
}
