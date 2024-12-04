package com.shark.example.algorithm.leetcode.page2;

import java.util.HashMap;
import java.util.Map;

public class Program76 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> checkMap = generateCheckMap(t);
        int min = Integer.MAX_VALUE;
        int resultLeft = 0;
        int resultRight = 0;
        Map<Character, Integer> inputMap = new HashMap<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            inputMap.put(c1, inputMap.getOrDefault(c1, 0) + 1);
            while (isValid(inputMap, checkMap)) {
                int temp = i - j + 1;
                if (temp < min) {
                    min = temp;
                    resultRight = i;
                    resultLeft = j;
                }
                char c2 = s.charAt(j);
                inputMap.put(c2, inputMap.getOrDefault(c2, 0) - 1);
                j ++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(resultLeft, resultRight + 1);
    }

    private Map<Character, Integer> generateCheckMap(String checkText) {
        Map<Character, Integer> checkMap = new HashMap<>();
        for (int i = 0; i < checkText.length(); i++) {
            Character c = checkText.charAt(i);
            checkMap.put(c, checkMap.getOrDefault(c, 0) + 1);
        }
        return checkMap;
    }

    private boolean isValid(Map<Character, Integer> inputMap, Map<Character, Integer> checkMap) {
        for(Character key : checkMap.keySet()) {
            int checkCount = checkMap.get(key);
            int inputCount = inputMap.getOrDefault(key, 0);
            if (inputCount < checkCount) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Program76().minWindow("ADOBECODEBANC", "ABC"));
    }
}
