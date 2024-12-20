package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }

        if (obstacleGrid.length == 1 && obstacleGrid[0].length == 1) {
            return 1;
        }
        int[][] result = new int[obstacleGrid.length][obstacleGrid[0].length];
        result[0][0] = 1;
        for (int i = 1; i < obstacleGrid.length; i++) {
            int obstacle = obstacleGrid[i][0];
            if (obstacle == 0) {
                result[i][0] = result[i - 1][0];
            } else {
                result[i][0] = 0;
            }
        }
        for (int i = 1; i < obstacleGrid[0].length; i++) {
            int obstacle = obstacleGrid[0][i];
            if (obstacle == 0) {
                result[0][i] = result[0][i - 1];
            } else {
                result[0][i] = 0;
            }
        }
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                int obstacle = obstacleGrid[i][j];
                if (obstacle == 0) {
                    result[i][j] = result[i][j - 1] + result[i - 1][j];
                } else {
                    result[i][j] = 0;
                }
            }
        }
        System.out.println(new Gson().toJson(result));
        return result[result.length - 1][result[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(new Program63().uniquePathsWithObstacles(grid));
    }
}
