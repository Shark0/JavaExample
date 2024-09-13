package com.shark.example.algorithm.leetcode.program36;

import com.google.gson.Gson;

public class Program36 {

    public boolean isValidSudoku(char[][] board) {
        //check row
        Gson gson = new Gson();
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            boolean[] rows = new boolean[9];
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                String stringValue = String.valueOf(board[rowIndex][columnIndex]);
                System.out.println("rowIndex: " + rowIndex + ", columnIndex: " + columnIndex + ", stringValue: " + stringValue);
                if(!".".equalsIgnoreCase(stringValue)) {
                    int intValueIndex = Integer.parseInt(stringValue) - 1;
                    if (rows[intValueIndex]) {
                        System.out.println("rows: " + gson.toJson(rows));
                        return false;
                    }
                    rows[intValueIndex] = true;
                }
            }
        }
        //check column
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            boolean[] columns = new boolean[9];
            for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
                String stringValue = String.valueOf(board[rowIndex][columnIndex]);
//                System.out.println("rowIndex: " + rowIndex + ", columnIndex: " + columnIndex + ", stringValue: " + stringValue);
                if(!".".equalsIgnoreCase(stringValue)) {
                    int intValueIndex = Integer.parseInt(stringValue) - 1;
                    if (columns[intValueIndex]) {
                        System.out.println("columns: " + gson.toJson(columns));
                        return false;
                    }
                    columns[intValueIndex] = true;
                }
            }
        }
        //check block
        for(int i = 0; i < 9; i++) {
            int rowIndexOffset = (i / 3) * 3;
            int columnIndexOffset = (i % 3) * 3;
            boolean[] blocks = new boolean[9];
            System.out.println("block i = " + i + ", rowIndexOffset: " + rowIndexOffset + ", columnIndexOffset: " + columnIndexOffset);
            for(int rowIndex = 0; rowIndex < 3; rowIndex++) {
                for(int columnIndex = 0; columnIndex < 3; columnIndex++) {
                    String stringValue = String.valueOf(board[rowIndex + rowIndexOffset][columnIndex + columnIndexOffset]);
//                    System.out.println("rowIndex: " + rowIndex + ", columnIndex: " + columnIndex + ", stringValue: " + stringValue);
                    if(!".".equalsIgnoreCase(stringValue)) {
                        int intValueIndex = Integer.parseInt(stringValue) - 1;
                        if (blocks[intValueIndex]) {

                            System.out.println("blocks: " + gson.toJson(blocks));
                            return false;
                        }
                        blocks[intValueIndex] = true;
                    }
                }
            }
        }

        return true;
    }

    public void printBoard(char[][] board) {
        Gson gson = new Gson();
        for (char[] row : board) {
            System.out.println(gson.toJson(row));
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Program36 program36 = new Program36();
        program36.printBoard(board);
        System.out.println(program36.isValidSudoku(board));
    }

}
