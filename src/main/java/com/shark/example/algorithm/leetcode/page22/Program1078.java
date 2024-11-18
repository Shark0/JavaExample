package com.shark.example.algorithm.leetcode.page22;

import java.util.ArrayList;
import java.util.List;

public class Program1078 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] textArray = text.split(" ");
        List<String> results = new ArrayList<>();
        int i = 2;
        while ( i < textArray.length) {
            if(textArray[i - 2].equalsIgnoreCase(first) && textArray[i - 1].equalsIgnoreCase(second)) {
                results.add(textArray[i]);
            }
            i ++;
        }
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
    }
}
