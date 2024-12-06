package com.shark.example.algorithm.leetcode.page8;

import java.util.HashMap;
import java.util.Map;

public class Program383 {

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            Character c = magazine.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int j = 0; j < ransomNote.length(); j++) {
            Character c = ransomNote.charAt(j);
            Integer count = map.get(c);
            if (count != null && count > 0) {
                map.put(c, count - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Program383().canConstruct("aa", "ab"));
    }
}
