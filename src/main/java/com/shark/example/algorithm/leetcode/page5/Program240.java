package com.shark.example.algorithm.leetcode.page5;

public class Program240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            System.out.println("i = " + i + ", j = " + j + ", matrix[i][j] = " + matrix[i][j] + ". target = " + target);
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
//        System.out.println(new Program240().searchMatrix(matrix, 19));

        int[][] matrix = {{-1, 3}};
        System.out.println(new Program240().searchMatrix(matrix, 3));
    }
}
