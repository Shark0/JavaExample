package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.*;

public class Program73 {

    public void setZeroes(int[][] matrix) {
        List<int[]> zeroes = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroes.add(new int[]{i, j});
                }
            }
        }
        Set<Integer> iSet = new HashSet<>();
        Set<Integer> jSet = new HashSet<>();
        for (int[] zeroLocation: zeroes) {
            int i = zeroLocation[0];
            if(!iSet.contains(i)){
                iSet.add(i);
                Arrays.fill(matrix[i], 0);
            }
            int j = zeroLocation[1];
            if(!jSet.contains(j)){
                jSet.add(j);
                for(int k = 0; k < matrix.length; k++) {
                    matrix[k][j] = 0;
                }
            }
        }
    }

    public static void main(String[] argv) {
        int[][] matrix = new int[][]{{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        new Program73().setZeroes(matrix);
        System.out.println(new Gson().toJson(matrix));
    }
}
