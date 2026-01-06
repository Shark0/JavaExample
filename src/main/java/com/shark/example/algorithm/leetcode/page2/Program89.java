package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program89 {

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int size = 1 << n;
        for (int i = 0; i < size; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    public static void main(String[] argv) {
        List<Integer> result = new Program89().grayCode(2);
        System.out.println(new Gson().toJson(result));
    }

}
