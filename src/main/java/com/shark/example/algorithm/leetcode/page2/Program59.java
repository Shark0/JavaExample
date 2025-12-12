package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program59 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int status = 0;
        int number = 1;
        int x = 0, y = 0;

        while (number < n * n + 1) {
            switch (status) {
                case 0: // left to right
                    if (y < n && result[x][y] == 0) {
                        result[x][y] = number;
                        y ++;
                        number ++;
                    } else {
                        status = 1;
                        y --;
                        x ++;
                    }
                    break;
                case 1: // up to down
                    if (x < n && result[x][y] == 0) {
                        result[x][y] = number;
                        x ++;
                        number ++;
                    } else {
                        status = 2;
                        x --;
                        y --;
                    }
                    break;
                case 2: // right to left
                    if (y >= 0 && result[x][y] == 0) {
                        result[x][y] = number;
                        y --;
                        number ++;
                    } else {
                        status = 3;
                        y ++;
                        x --;
                    }
                    break;
                case 3: // up to down
                    if (x >= 0 && result[x][y] == 0) {
                        result[x][y] = number;
                        x --;
                        number ++;
                    } else {
                        status = 0;
                        x ++;
                        y ++;
                    }
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] result = new Program59().generateMatrix(5);
        String json = new Gson().toJson(result);
        System.out.println(json);
    }
}
