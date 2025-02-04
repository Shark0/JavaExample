package com.shark.example.algorithm.leetcode.page19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.shark.example.algorithm.leetcode.LeetcodeUtil;

public class Program909 {

    public int snakesAndLadders(int[][] board) {
        int[] path = generatePath(board);
        System.out.println("path: " + Arrays.toString(path));
        int length = board.length * board[0].length;
        int[] visited = new int[length];
        //init
        List<Integer> locationList = List.of(0);
        visited[0] = 1;
        //graph bfs
        int step = 1;
        while (!locationList.isEmpty()) {
            System.out.println("step: " + step +  ", locationList: " + new Gson().toJson(locationList));
            List<Integer> nextLocationList = new ArrayList<>();
            for(Integer location : locationList) {
                for(int dice = 1; dice <= 6; dice++) {
                    if(location + dice >= length - 1 || path[location + dice] >= length - 1) {
                        System.out.println("location: " + location +  ", dice: " + dice);
                        return step;
                    }
                    if(visited[path[location + dice]] == 1) {
                        continue;
                    }
                    visited[path[location + dice]] = 1;

                    System.out.println("step: " + step +  ", location: " + location +  ", dice: " + dice + ", location + dice: " + (location + dice));
                    nextLocationList.add(path[location + dice]);
                }
            }
            locationList = nextLocationList;
            step++;
        }


        return -1;
    }


    public int[] generatePath(int[][] board) {
        int[] path = new int[board.length * board[0].length];
        int index = 0;
        for (int i = 1; i <= board.length; i++) {
            int[] row = board[board.length - i];
            if (i % 2 == 0) {
                for(int j = row.length - 1; j >= 0; j--) {
                    int value = row[j];
                    if(value != -1) {
                        path[index] = value - 1; //transfer to index
                    } else {
                        path[index] = index;
                    }
                    index ++;
                }
            } else {
                for (int value : row) {
                    if (value != -1) {
                        path[index] = value - 1; //transfer to index
                    } else {
                        path[index] = index;
                    }
                    index++;
                }
            }
        }
        return path;
    }

    public static void main(String[] args) {

        int[][] board = {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };

//        board = new int[][]{
//                {-1,-1,-1},
//                {-1,9,8},
//                {-1,8,9}
//        };

//        board = new int[][]{{-1,1,2,-1},{2,13,15,-1},{-1,10,-1,-1},{-1,6,2,8}};
        LeetcodeUtil.print2dIntArray(board);
        System.out.println(new Program909().snakesAndLadders(board) == 3);
    }
}
