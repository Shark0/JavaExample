package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program51 {


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int[][] matrix = new int[n][n];
            results.addAll(solveNQueens(matrix, i, 0, n));

        }
        return results;
    }

    private List<List<String>> solveNQueens(int[][] matrix, int x, int y, int n) {
        List<List<String>> results = new ArrayList<>();
        matrix[y][x] = 1;
        boolean check = check(matrix, x, y);
        if(check) {
            if(y == n - 1) {
                results.add(generateListResult(matrix));
            } else {
                for(int i = 0; i < n; i++) {
                    results.addAll(solveNQueens(matrix, i, y + 1, n));
                }
            }
        }
        matrix[y][x] = 0;
        return results;
    }

    private boolean check(int[][] matrix, int x, int y) {
        int checkX;
        int checkY;
        //check up
        checkY = y - 1;
        checkX = x;
        while (checkY >= 0) {
            if (matrix[checkY][checkX] == 1) {
//                System.out.println("check top false x: " + checkX + ", y: " + checkY);
                return false;
            }
            checkY--;
        }
        //check up left
        checkY = y - 1;
        checkX = x - 1;
        while (checkX >= 0 && checkY >= 0) {
            if (matrix[checkY][checkX] == 1) {
//                System.out.println("check up-left false x: " + checkX + ", y: " + checkY);
                return false;
            }
            checkX--;
            checkY--;
        }
        //check up right
        checkY = y - 1;
        checkX = x + 1;
        while (checkX <= matrix.length - 1 && checkY >= 0) {
            if (matrix[checkY][checkX] == 1) {
//                System.out.println("check up-right false x: " + checkX + ", y: " + checkY);
                return false;
            }
            checkX++;
            checkY--;
        }

        return true;
    }

    private List<String> generateListResult(int[][] matrix) {
        List<String> list = new ArrayList<>();
        for (int[] row: matrix) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int value : row) {
                if (value == 1) {
                    stringBuilder.append("Q");
                } else {
                    stringBuilder.append(".");
                }
            }
            list.add(stringBuilder.toString());
        }
        return list;
    }

    public static void main(String[] argv) {
        Program51 program51 = new Program51();
        List<List<String>> results = program51.solveNQueens(5);
        System.out.println(new Gson().toJson(results));
    }
}
