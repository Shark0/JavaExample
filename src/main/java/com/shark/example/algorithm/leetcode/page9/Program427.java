package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

public class Program427 {


    public Node construct(int[][] grid) {
        Node node = new Node();
        if (grid.length == 1) {
            node.isLeaf = true;
            node.val = grid[0][0] == 1;
            return node;
        } else {
            int offset = grid.length / 2;
            int[][] topLeft = new int[grid.length / 2][grid.length / 2];
            int[][] topRight = new int[grid.length / 2][grid.length / 2];
            int[][] bottomLeft = new int[grid.length / 2][grid.length / 2];
            int[][] bottomRight = new int[grid.length / 2][grid.length / 2];
            for (int i = 0; i < grid.length / 2; i++) {
                for (int j = 0; j < grid.length / 2; j++) {
//                    System.out.println("i = " + i + ", j = " + j + ", i + offset = " + (i + offset) + ", j + offset = " + (j + offset));
                    topLeft[i][j] = grid[i][j];
                    topRight[i][j] = grid[i][j + offset];
                    bottomLeft[i][j] = grid[i + offset][j];
                    bottomRight[i][j] = grid[i + offset][j + offset];
                }
            }
            int topLeftValue = countValue(topLeft);
            int topRightValue = countValue(topRight);
            int bottomLeftValue = countValue(bottomLeft);
            int bottomRightValue = countValue(bottomRight);
            if (topLeftValue == topRightValue && topRightValue == bottomLeftValue && bottomLeftValue == bottomRightValue) {
                if (topLeftValue == 0) {
                    node.isLeaf = true;
                    node.val = false;
                    return node;
                } else if (topLeftValue == (grid.length * grid.length / 4)) {
                    node.isLeaf = true;
                    node.val = true;
                    return node;
                }
            }
            node.isLeaf = false;
            node.val = false;
            node.topLeft = construct(topLeft);
            node.topRight = construct(topRight);
            node.bottomLeft = construct(bottomLeft);
            node.bottomRight = construct(bottomRight);
            return node;
        }
    }

    private int countValue(int[][] board) {
        int count = 0;
        for (int[] row : board) {
            for (int j = 0; j < board[0].length; j++) {
                count = count + row[j];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
        };
        System.out.println(new Gson().toJson(new Program427().construct(board)));
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
