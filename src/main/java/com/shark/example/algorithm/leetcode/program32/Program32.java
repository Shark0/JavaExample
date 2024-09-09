package com.shark.example.algorithm.leetcode.program32;

import java.util.ArrayList;
import java.util.List;

public class Program32 {
    public int longestValidParentheses(String s) {
        boolean[] results = new boolean[s.length()];
        List<Integer> leftList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftList.add(0, i);
            } else {
                if(!leftList.isEmpty()){
                    Integer leftIndex = leftList.get(0);
                    results[leftIndex] = true;
                    results[i] = true;
                    leftList.remove(0);
                }
            }
        }

        int lastLength = 0;
        int currentIndex = -1;

        for(int i = 0; i < results.length; i++){
            if(results[i]){
                if(currentIndex == -1) {
                    currentIndex = i;
                }
                int currentLength = i - currentIndex + 1;
                if(currentLength > lastLength){
                    lastLength = currentLength;
                }
            } else {
                currentIndex = -1;
            }
        }

        // System.out.println(new Gson().toJson(results));
        return lastLength;
    }

    public static void main(String[] args) {
        String input = "(()";
        Program32 program32 = new Program32();
        System.out.println("result: " + program32.longestValidParentheses(input));
    }
}
