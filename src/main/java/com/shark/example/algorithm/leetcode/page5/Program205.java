package com.shark.example.algorithm.leetcode.page5;

import java.util.HashMap;
import java.util.Map;

public class Program205 {

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c1 = s.charAt(i);
            char c2 = t.charAt(i);
            Character value1 = map1.get(c1);
            Character value2 = map2.get(c2);
            if(value1 == null && value2 == null) {
                map1.put(c1, c2);
                map2.put(c2, c1);
            } else {
                if(value1 == null || value2 == null) {
                    return false;
                }
                if(value1 != c2 || value2 != c1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Program205().isIsomorphic("ab", "cd"));
    }
}
