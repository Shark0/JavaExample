package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program79 {

    public boolean exist(char[][] board, String word) {
        int count = board[0].length * board.length;
        int[][] checkBoard = new int[board.length][board[0].length];
        char wordChar = word.charAt(0);
        for (int i = 0; i < count; i++) {
            int row = i / board[0].length;
            int column = i % board[0].length;
            char boardChar = board[row][column];
            if(wordChar == boardChar) {
                if(word.length() == 1) {
                    return true;
                }
                checkBoard[row][column] = 1;
                boolean find = find(board, checkBoard, row, column, word, 1);
                if(find) {
                    return true;
                }
                checkBoard[row][column] = 0;
            }
        }

        return false;
    }

    private boolean find(char[][] board, int[][] checkBoard, int row, int column, String word, int wordCharIndex) {
        System.out.println("row: " + row + ", column: " + column + ", wordIndex: " + wordCharIndex);
        printBoard(checkBoard);
        char checkWordChar = word.charAt(wordCharIndex);
        //check left
        int checkRowIndex = row;
        int checkColumnIndex = column -1;
        if(checkColumnIndex >= 0 && checkBoard[checkRowIndex][checkColumnIndex] == 0) {
            char boardChar = board[checkRowIndex][checkColumnIndex];
            if(boardChar == checkWordChar) {
                if(wordCharIndex == word.length() - 1) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 1;
                boolean find = find(board, checkBoard, checkRowIndex, checkColumnIndex, word, wordCharIndex + 1);
                if(find) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 0;
            }
        }
        //check up
        checkRowIndex = row - 1;
        checkColumnIndex = column;
        if(checkRowIndex >= 0 && checkBoard[checkRowIndex][checkColumnIndex] == 0) {
            char boardChar = board[checkRowIndex][checkColumnIndex];
            if(boardChar == checkWordChar) {
                if(wordCharIndex == word.length() - 1) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 1;
                boolean find = find(board, checkBoard, checkRowIndex, checkColumnIndex, word, wordCharIndex + 1);
                if(find) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 0;
            }
        }
        //check right
        checkRowIndex = row;
        checkColumnIndex = column + 1;
        if(checkColumnIndex < board[0].length && checkBoard[checkRowIndex][checkColumnIndex] == 0) {
            char boardChar = board[checkRowIndex][checkColumnIndex];
            if(boardChar == checkWordChar) {
                if(wordCharIndex == word.length() - 1) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 1;
                boolean find = find(board, checkBoard, checkRowIndex, checkColumnIndex, word, wordCharIndex + 1);
                if(find) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 0;
            }
        }
        //check down
        checkRowIndex = row + 1;
        checkColumnIndex = column;
        if(checkRowIndex < board.length && checkBoard[checkRowIndex][checkColumnIndex] == 0) {
            char boardChar = board[checkRowIndex][checkColumnIndex];
            if(boardChar == checkWordChar) {
                if(wordCharIndex == word.length() - 1) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 1;
                boolean find = find(board, checkBoard, checkRowIndex, checkColumnIndex, word, wordCharIndex + 1);
                if(find) {
                    return true;
                }
                checkBoard[checkRowIndex][checkColumnIndex] = 0;
            }
        }
        return false;
    }

    public void printBoard(int[][] board) {
        for(int[] row : board) {
            System.out.println(new Gson().toJson(row));
        }
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "SEE";
        System.out.println(new Program79().exist(board, word));
    }
}
