package com.shark.example.algorithm.leetcode.program15;

import com.google.gson.Gson;

import java.util.Stack;

public class Program739 {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] results = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < temperatures.length; i++){
            int value = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peek()] < value){
                int stackIndex = stack.pop();
                results[stackIndex] = i - stackIndex;
            }
            stack.push(i);
        }

        return results;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] results = new Program739().dailyTemperatures(temperatures);
        System.out.println("results: " + new Gson().toJson(results));
    }
}
