package com.shark.example.algorithm.leetcode.page6;

import java.util.HashMap;
import java.util.Map;

public class Program290 {

    public boolean wordPattern(String s, String t) {
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        String[] words = t.split(" ");
        if(s.length() != words.length) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            Character c1 = s.charAt(i);
            String word = words[i];
            String value1 = map1.get(c1);
            Character value2 = map2.get(word);
            if(value1 == null && value2 == null) {
                map1.put(c1, word);
                map2.put(word, c1);
            } else {
                if(value1 == null || value2 == null) {
                    return false;
                }
                if(!value1.equalsIgnoreCase(word) || value2 != c1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Program290().wordPattern("abba", "dog cat cat dog"));
    }
}
