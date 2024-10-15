package com.shark.example.algorithm.leetcode.page13;

import java.util.HashSet;
import java.util.Set;

public class Program649 {

    public String predictPartyVictory(String senate) {
        Set<Character> set = new HashSet<>();
        int rSkipCount = 0;
        int dSkipCount = 0;
        while (set.size() != 1) {
            set.clear();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < senate.length(); i++) {
                char c = senate.charAt(i);
                if (c == 'R') {
                    if (rSkipCount > 0) {
                        rSkipCount--;
                    } else {
                        dSkipCount++;
                        stringBuilder.append(c);
                        set.add(c);
                    }
                } else if (c == 'D') {
                    if (dSkipCount > 0) {
                        dSkipCount--;
                    } else {
                        rSkipCount++;
                        stringBuilder.append(c);
                        set.add(c);
                    }
                }
            }
            senate = stringBuilder.toString();
        }

        if(set.contains('R')) {
            return "Radiant";
        } else {
            return "Dire";
        }

    }

    public static void main(String[] args) {
        Program649 program649 = new Program649();
        String result = program649.predictPartyVictory("RDD");
        System.out.println("result: " + result);
    }
}
