package com.shark.example.algorithm.leetcode.page34;

import java.util.HashMap;
import java.util.Map;

public class Program1657 {

    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) {
            System.out.println("word1.length() != word2.length()");
            return false;
        }
        Map<Character, Integer> word1CharCountMap = new HashMap<>();
        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            int count = word1CharCountMap.getOrDefault(c, 0);
            count++;
            word1CharCountMap.put(c, count);
        }

        Map<Character, Integer> word2CharCountMap = new HashMap<>();
        for (int i = 0; i < word2.length(); i++) {
            char c = word2.charAt(i);
            int charCount = word2CharCountMap.getOrDefault(c, 0);
            charCount++;
            word2CharCountMap.put(c, charCount);
        }
        if(word1CharCountMap.size() != word2CharCountMap.size()) {
            return false;
        }

        for(char c1: word1CharCountMap.keySet()) {
            if(!word2CharCountMap.containsKey(c1)) {
                return false;
            }
        }

        Map<Integer, Integer> word1CountCharCountMap = new HashMap<>();
        for(Character c : word1CharCountMap.keySet()) {
            int count = word1CharCountMap.get(c);
            int charCount =  word1CountCharCountMap.getOrDefault(count, 0);
            charCount ++;
            word1CountCharCountMap.put(count, charCount);
        }

        Map<Integer, Integer> word2CountCharCountMap = new HashMap<>();
        for(Character c : word2CharCountMap.keySet()) {
            int count = word2CharCountMap.get(c);
            int charCount =  word2CountCharCountMap.getOrDefault(count, 0);
            charCount ++;
            word2CountCharCountMap.put(count, charCount);
        }

        if(word1CountCharCountMap.keySet().size() != word2CountCharCountMap.keySet().size()) {
            System.out.println("word1CountCharCountMap.keySet().size() != word2CountCharCountMap.keySet().size()");
            return false;
        }

        for(Integer count : word1CountCharCountMap.keySet()) {
            Integer word1CharCount = word1CountCharCountMap.get(count);
            Integer word2CharCount = word2CountCharCountMap.get(count);
            if(word2CharCount == null ||
                    word1CharCount.intValue() != word2CharCount.intValue()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Program1657 program1657 = new Program1657();
        System.out.println(program1657.closeStrings("cabbba", "abbccc"));
    }
}
