package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.HashMap;

public class Program96 {

    private final HashMap<Integer, Integer> cacheMap = new HashMap<>();

    public int numTrees(int n) {
        Integer cache = cacheMap.get(n);
        if(cache != null) {
            return cache;
        }
        int result = 0;
        for(int i = 0; i < n; i ++) {
            //left
            int left = 1;
            if(i <= 1) {
                left = 1;
            } else if(i == 2) {
                left = 2;
            } else {
                left = numTrees(i);
            }
            //right
            int rightNum = n - i - 1;
            int right = 1;
            if(rightNum <= 1) {
                right = 1;
            } else if(rightNum == 2) {
                right = 2;
            } else {
                right = numTrees(rightNum);
            }
            result = result + left * right;
        }

        cacheMap.put(n, result);
        return result;
    }

    public static void main(String[] argv) {
        Program96 program96 = new Program96();
        System.out.println(program96.numTrees(3));
    }
}
