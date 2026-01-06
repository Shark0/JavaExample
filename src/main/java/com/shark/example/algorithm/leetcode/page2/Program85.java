package com.shark.example.algorithm.leetcode.page2;

public class Program85 {

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            if ((matrix.length - i) * matrix[0].length <= max) {
                System.out.println("return");
                return max;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int tempMax = calculateTempMax(matrix, i, j);
                System.out.println("i: " + i + ", j: " + j + ", tempMax: " + tempMax);
                if (tempMax > max) {
                    max = tempMax;
                }
            }
        }
        return max;
    }

    private int calculateTempMax(char[][] matrix, int i, int j) {
        int max = 0;
        int height = 0;
        int width = matrix[0].length - j;

        for (int x = i; x < matrix.length; x++) {
            int tempWidth = 0;
            if (matrix[x][j] == '0') {
                return max;
            }
            height++;
            for (int y = j; y < matrix[0].length; y++) {
                if (matrix[x][y] == '0') {
                    break;
                }
                tempWidth++;
            }
            if(tempWidth < width) {
                width = tempWidth;
            }
            if(width == 0) {
                return max;
            }
            int tempMax = height * width;
            if(tempMax > max) {
                max = tempMax;
            }
            System.out.println("i: " + i + ", j: " + j + ", height: " + height + ", width: " + width + ", max: " + max);
        }

        return max;
    }


    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '1', '1'}
        };
        System.out.printf("max: " + new Program85().maximalRectangle(matrix));
    }
}
