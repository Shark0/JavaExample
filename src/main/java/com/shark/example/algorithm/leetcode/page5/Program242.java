package com.shark.example.algorithm.leetcode.page5;

import java.util.HashMap;
import java.util.Map;

public class Program242 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = map.get(c);
            if (count == null) {
                return false;
            }
            if (count > 0) {
                count--;
            }
            if (count == 0) {
                map.remove(c);
            } else {
                map.put(c, count);
            }
        }

        return map.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Program242().isAnagram("anagram", "nagaram"));
    }
}
