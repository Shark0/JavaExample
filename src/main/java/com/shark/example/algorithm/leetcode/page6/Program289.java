package com.shark.example.algorithm.leetcode.page6;

import com.google.gson.Gson;

public class Program289 {

    public void gameOfLife(int[][] board) {
        int[][] neighborsCounts = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i - 1 >= 0) {
                    //up left
                    if (j - 1 >= 0) {
                        if(board[i - 1][j - 1] == 1) {
                            neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                        }
                    }
                    //up
                    if(board[i - 1][j] == 1) {
                        neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                    }
                    //up right
                    if(j + 1 < board[0].length) {
                        if(board[i - 1][j + 1] == 1) {
                            neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                        }
                    }
                }
                //left
                if (j - 1 >= 0) {
                    if(board[i][j - 1] == 1) {
                        neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                    }
                }
                //right
                if(j + 1 < board[0].length) {
                    if(board[i][j + 1] == 1) {
                        neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                    }
                }
                if (i + 1 < board.length) {
                    //down left
                    if (j - 1 >= 0) {
                        if(board[i + 1][j - 1] == 1) {
                            neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                        }
                    }
                    //down
                    if(board[i + 1][j] == 1) {
                        neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                    }
                    //down right
                    if(j + 1 < board[0].length) {
                        if(board[i + 1][j + 1] == 1) {
                            neighborsCounts[i][j] = neighborsCounts[i][j] + 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int live = board[i][j];
                int neighborCount = neighborsCounts[i][j];
                if (live == 0) {
                    if (neighborCount == 3) {
                        board[i][j] = 1;
                    }
                } else {
                    if (neighborCount < 2 || neighborCount > 3) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        new Program289().gameOfLife(matrix);
        System.out.println(new Gson().toJson(matrix));
    }
}
