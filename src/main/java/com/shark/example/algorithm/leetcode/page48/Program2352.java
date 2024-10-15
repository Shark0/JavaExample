package com.shark.example.algorithm.leetcode.page48;

public class Program2352 {

    public int equalPairs(int[][] grid) {
        int equalCount = 0;
        for (int[] row: grid) {
            for(int colundIndex = 0; colundIndex < row.length; colundIndex++) {
                boolean isEqual = true;
                int rowIndex = 0;
                for(int value: row) {
                    isEqual = value == grid[rowIndex][colundIndex];
                    if(!isEqual) {
                        break;
                    }
                    rowIndex ++;
                }
                if(isEqual) {
                    equalCount++;
                }
            }

        }
        return equalCount;
    }

    public static void main(String[] args) {
        Program2352 program2352 = new Program2352();
        int count = program2352.equalPairs(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}});
        System.out.println("count: " + count);
    }
}
