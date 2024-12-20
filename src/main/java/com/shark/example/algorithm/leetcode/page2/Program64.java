package com.shark.example.algorithm.leetcode.page2;

public class Program64 {

    public int minPathSum(int[][] grid) {
        int[][] result = new int[grid.length][grid[0].length];
        result[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            result[i][0] = result[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            result[0][i] = result[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                int gridValue = grid[i][j];
                int topResult = result[i - 1][j] + gridValue;
                int leftResult = result[i][j - 1] + gridValue;
                result[i][j] = Math.min(topResult, leftResult);
            }
        }
        return result[result.length - 1][result[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Program64().minPathSum(grid));
    }
}
