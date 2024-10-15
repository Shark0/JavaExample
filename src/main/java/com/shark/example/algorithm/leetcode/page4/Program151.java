package com.shark.example.algorithm.leetcode.page4;

import com.google.gson.Gson;

public class Program151 {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        System.out.println(new Gson().toJson(words));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if(!words[i].isEmpty()) {
                stringBuilder.append(words[i]);
                if(i != 0) {
                    stringBuilder.append(" ");
                }
            }
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        Program151 program151 = new Program151();
        String result = program151.reverseWords("  Hello World  ");
        System.out.println("result: " + result);
    }
}
