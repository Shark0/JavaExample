package com.shark.example.algorithm.leetcode.page2;

public class Program74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length  * matrix[0].length- 1;
        while (start <= end) {
            int index = (start + end) / 2;
            int row = index / matrix[0].length;
            int column = index % matrix[0].length;
            int value = matrix[row][column];
            System.out.println("row: " + row + ", column: " + column + ", index: " + index + ", value: " + value + ", target: " + target);
            if (value == target) {
                return true;
            } else if (value > target) {
                end = index - 1;
            } else {
                start = start + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1},
                {3},
                {5}
        };
        int target = 3;
        System.out.println(new Program74().searchMatrix(matrix, target));
    }
}
