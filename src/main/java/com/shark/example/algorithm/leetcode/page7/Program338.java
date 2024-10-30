package com.shark.example.algorithm.leetcode.page7;

import com.google.gson.Gson;

public class Program338 {

    public int[] countBits(int n) {
        int[] results = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            results[i] = countBit(i);
        }
        return  results;
    }

    public int countBit(int number) {
        int result = 0;
        while(number != 0) {
            if((number & 1) == 1) {
                result ++;
            }
            number =  number >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("results: " + new Gson().toJson(new Program338().countBits(5)));
    }
}
