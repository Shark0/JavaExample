package com.shark.example.algorithm.leetcode.page20;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program994 {

    public int orangesRotting(int[][] grid) {
        List<int[]> nextNodeList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    nextNodeList.add(new int[]{i, j});
                }
            }
        }

        int result = 0;
        if(!nextNodeList.isEmpty()) {
            result = bfs(grid, nextNodeList);
        }

        boolean hasFresh = false;
        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (row[j] == 1) {
                    hasFresh = true;
                    break;
                }
            }
            if (hasFresh) {
                break;
            }
        }
        return hasFresh ? -1 : result;
    }


    public int bfs(int[][] grid, List<int[]> nodes) {
        List<int[]> nextNodes = new ArrayList<>();
        for (int[] node : nodes) {
            int y = node[0];
            int x = node[1];

            //check up
            if (y > 0) {
                if (grid[y - 1][x] == 1) {
                    grid[y - 1][x] = 2;
                    nextNodes.add(new int[]{y - 1, x});
                }
            }
            //check left
            if (x > 0) {
                if (grid[y][x - 1] == 1) {
                    grid[y][x - 1] = 2;
                    nextNodes.add(new int[]{y, x - 1});
                }
            }
            //check down
            if (y < grid.length - 1) {
                if (grid[y + 1][x] == 1) {
                    grid[y + 1][x] = 2;
                    nextNodes.add(new int[]{y + 1, x});
                }
            }
            //check right
            if (x < grid[0].length - 1) {
                if (grid[y][x + 1] == 1) {
                    grid[y][x + 1] = 2;
                    nextNodes.add(new int[]{y, x + 1});
                }
            }

        }
        if (nextNodes.isEmpty()) {
            return 0;
        } else {
            return 1 + bfs(grid, nextNodes);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1},
        };
        System.out.println("result: " + new Gson().toJson(new Program994().orangesRotting(grid)));
    }
}
