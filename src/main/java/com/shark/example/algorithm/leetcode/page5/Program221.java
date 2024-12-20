package com.shark.example.algorithm.leetcode.page5;

public class Program221 {
    public int maximalSquare(char[][] matrix) {
        int[][] square = new int[matrix.length][matrix[0].length];
        int[][] leftEdges = new int[matrix.length][matrix[0].length];
        int[][] upEdges = new int[matrix.length][matrix[0].length];
        System.out.println("rowCount: " + matrix.length );
        System.out.println("columnCount: " + matrix[0].length );
        int max = 0;
        for (int i = 0; i < leftEdges.length; i++) {
            for (int j = 0; j < leftEdges[i].length; j++) {
                int value = matrix[i][j] - '0';
                if (value == 0) {
                    leftEdges[i][j] = value;
                } else {
                    max = 1;
                    if (j == 0) {
                        leftEdges[i][j] = 1;
                        square[i][j] = 1;
                    } else {
                        leftEdges[i][j] = leftEdges[i][j - 1] + 1;
                    }
                }
            }
        }
        System.out.println("left Edge");
        printMatrix(leftEdges);

        for (int i = 0; i < upEdges.length; i++) {
            for (int j = 0; j < upEdges[i].length; j++) {
                int value = matrix[i][j] - '0';
                if (value == 0) {
                    upEdges[i][j] = value;
                } else {
                    max = 1;
                    if (i == 0) {
                        upEdges[i][j] = 1;
                        square[i][j] = 1;
                    } else {
                        upEdges[i][j] = upEdges[i - 1][j] + 1;
                    }
                }
            }
        }
        System.out.println("up Edge");
        printMatrix(upEdges);

        for (int i = 1; i < square.length; i++) {
            for (int j = 1; j < square[i].length; j++) {
                int previewSquare = square[i - 1][j - 1];
                int matrixValue = matrix[i][j] - '0';
                int upEdge = upEdges[i - 1][j];
                int leftEdge = leftEdges[i][j-1];
                if(previewSquare == 0 || matrixValue == 0 || upEdge == 0 || leftEdge == 0) {
                    square[i][j] = matrixValue;
                } else {
                    if(upEdge >= previewSquare && leftEdge >= previewSquare) {
                        square[i][j] = previewSquare + 1;
                    }  else {
                        int min = Math.min(upEdge, Math.min(leftEdge, previewSquare));
                        square[i][j] = min + 1;
                    }
                }
                max = Math.max(max, square[i][j]);
            }
        }
        System.out.println("square");
        printMatrix(square);

        return (int) Math.pow(max, 2);
    }

    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                if (i != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(row[i]);
            }
            System.out.println(stringBuilder);
        }
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'0', '0'},
                {'0', '1'}
        };
        System.out.println("max: " + new Program221().maximalSquare(matrix));
    }
}
