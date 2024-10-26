package com.shark.example.algorithm.leetcode.page39;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program1926 {
    public int nearestExit(char[][] maze, int[] entrance) {
        List<int[]> nodes = new ArrayList<>();
        nodes.add(entrance);
        maze[entrance[0]][entrance[1]] = '+';
        return bfs(maze, nodes);
    }

    public int bfs(char[][] maze, List<int[]> nodes) {
        int step = 0;
        while (!nodes.isEmpty()) {
            System.out.println("nodes: " + nodes.size() + ", step: " + step);
            printMaze(maze);

            List<int[]> nextNodes = new ArrayList<>();
            for (int[] node : nodes) {
                int y = node[0];
                int x = node[1];
                if(step > 0 && (y  == 0 || y == (maze.length - 1) ||
                        x == 0 || x == (maze[0].length - 1))) {
                    System.out.println("find it");
                    return step;
                }

                //check up
                if (y > 0) {
                    if (maze[y - 1][x] == '.') {
                        maze[y - 1][x] = '+';
                        nextNodes.add(new int[]{y - 1, x});
                    }
                }
                //check left
                if (x > 0) {
                    if (maze[y][x - 1] == '.') {
                        maze[y][x - 1] = '+';
                        nextNodes.add(new int[]{y, x - 1});
                    }
                }
                //check down
                if (y < maze.length - 1) {
                    if (maze[y + 1][x] == '.') {
                        maze[y + 1][x] = '+';
                        nextNodes.add(new int[]{y + 1, x});
                    }
                }
                //check right
                if (x < maze[0].length - 1) {
                    if (maze[y][x + 1] == '.') {
                        maze[y][x + 1] = '+';
                        nextNodes.add(new int[]{y, x + 1});
                    }
                }
            }
            step ++;
            nodes = nextNodes;
        }

        return -1;
    }

    public void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            System.out.println(new Gson().toJson(maze[i]));
        }
    }

    public static void main(String[] args) {
        char[][] maze = new char[][]{
                {'.','.','.','.','.','+','.','.','.'},
                {'.','+','.','.','.','.','.','.','.'},
                {'.','.','+','.','+','.','+','.','+'},
                {'.','.','.','.','+','.','.','.','.'},
                {'.','.','.','.','+','+','.','.','.'},
                {'+','.','.','.','.','.','.','.','.'},
                {'.','.','.','+','.','.','.','.','.'},
                {'.','.','.','+','.','.','.','.','+'},
                {'+','.','.','+','.','+','+','.','.'},
        };
        int[] entrance = new int[]{8, 4};
        System.out.println("result: " + new Program1926().nearestExit(maze, entrance));
    }

}
