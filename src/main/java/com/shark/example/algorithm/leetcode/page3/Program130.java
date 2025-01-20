package com.shark.example.algorithm.leetcode.page3;

import java.util.HashSet;
import java.util.Set;
import com.google.gson.Gson;

public class Program130 {
    public void solve(char[][] board) {
        if (board.length <= 2 || board[0].length <= 2) {
            return;
        }
        int[][] checkBoard = new int[board.length][board[0].length];
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[0].length - 1; j++) {
                int check = checkBoard[i][j];
                System.out.println("i: " + i + " j: " + j + " check: " + check);
                if (check == 1) {
                    continue;
                }
                checkBoard[i][j] = 1;
                char value = board[i][j];
                if (value == 'O') {
                    Set<int[]> checkPositionSet = new HashSet<>();
                    boolean isSurrender = checkSurrender(board, checkBoard, checkPositionSet, i, j);
                    System.out.println("i: " + i + ", j: " + j + " isSurrender: " + isSurrender);
                    if (isSurrender) {
                        for (int[] checkPosition : checkPositionSet) {
                            board[checkPosition[0]][checkPosition[1]] = 'X';
                        }
                    }
                }
            }
        }
    }

    private boolean checkSurrender(char[][] board, int[][] checkBoard, Set<int[]> checkPositionSet, int i, int j) {
        checkPositionSet.add(new int[]{i, j});
        checkBoard[i][j] = 1;
        char value = board[i][j];
        if (value == 'X') {
            return true;
        }

        if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
            return false;
        }

        boolean isUpSurrender = true;
        if (checkBoard[i - 1][j] == 0) {
            isUpSurrender = checkSurrender(board, checkBoard, checkPositionSet, i - 1, j);
        }
        boolean isDownSurrender = true;
        if (checkBoard[i + 1][j] == 0) {
            isDownSurrender = checkSurrender(board, checkBoard, checkPositionSet, i + 1, j);
        }
        boolean isLeftSurrender = true;
        if (checkBoard[i][j - 1] == 0) {
            isLeftSurrender = checkSurrender(board, checkBoard, checkPositionSet, i, j - 1);
        }
        boolean isRightSurrender = true;
        if (checkBoard[i][j + 1] == 0) {
            isRightSurrender = checkSurrender(board, checkBoard, checkPositionSet, i, j + 1);
        }
//        System.out.println("i: " + i + ", j: " + j);
//        System.out.println("isUpSurrender: " + isUpSurrender + ", isDownSurrender: " + isDownSurrender + ", isLeftSurrender: " + isLeftSurrender + ", isRightSurrender: " + isRightSurrender);
        return isUpSurrender && isDownSurrender && isLeftSurrender && isRightSurrender;
    }

    public void printBoard(char[][] board) {
        Gson gson = new Gson();
        for (char[] row : board) {
            System.out.println(gson.toJson(row));
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        Program130 program130 = new Program130();
        program130.solve(board);
        program130.printBoard(board);
    }
}
