package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program52 {
    public int totalNQueens(int n) {
        int[][] board = new int[n][n];
        return calculateResultCount(board, 0);
    }

    public int calculateResultCount(int[][] board, int row) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (row == 0 || check(board, row, i)) {
                if (row == board.length - 1) {
                    count++;
                } else {
                    board[row][i] = 1;
                    count = count + calculateResultCount(board, row + 1);
                    board[row][i] = 0;
                }
            }
        }
        return count;
    }

    public boolean check(int[][] board, int row, int column) {
//        System.out.println("row " + row + " column " + column);
//        printBoard(board);
        //check up-left
        int checkRow = row - 1;
        int checkColumn = column - 1;
        while (checkRow >= 0 && checkColumn >= 0) {
            if (board[checkRow][checkColumn] == 1) {
//                System.out.println("check up - left error");
                return false;
            }
            checkRow--;
            checkColumn--;
        }
        //check up
        checkRow = row - 1;
        checkColumn = column;
        while (checkRow >= 0) {
            if (board[checkRow][checkColumn] == 1) {
//                System.out.println("check up error ");
                return false;
            }
            checkRow--;
        }
        //check up-right
        checkRow = row - 1;
        checkColumn = column + 1;
        while (checkRow >= 0 && checkColumn < board.length) {
            if (board[checkRow][checkColumn] == 1) {
//                System.out.println("check up - right error ");
                return false;
            }
            checkRow--;
            checkColumn++;
        }
//        System.out.println("check ok");
        return true;
    }

    public void printBoard(int[][] board) {
        Gson gson = new Gson();
        for (int[] row : board) {
            System.out.println(gson.toJson(row));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Program52().totalNQueens(4));
    }
}
