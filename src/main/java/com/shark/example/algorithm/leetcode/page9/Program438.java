package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program438 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        if(s.length() < p.length()) {
            return results;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char sChar = s.charAt(i);
            char pChar = p.charAt(i);
            sMap.put(sChar, sMap.getOrDefault(sChar, 0) + 1);
            pMap.put(pChar, pMap.getOrDefault(pChar, 0) + 1);
        }
        for (int i = 0; i < (s.length() - p.length() + 1); i++) {
            if(i > 0) {
                char lastChar = s.charAt(i + p.length() - 1);
                sMap.put(lastChar, sMap.getOrDefault(lastChar, 0) + 1);
            }
            System.out.println("i: " + i + ", sMap: " + new Gson().toJson(sMap) + ", pMap: " + new Gson().toJson(pMap));
            if(pMap.equals(sMap)) {
                results.add(i);
            }
            //remove first
            char firstChar = s.charAt(i);
            int firstCount = sMap.getOrDefault(firstChar, 0) - 1;
            if(firstCount == 0) {
                sMap.remove(firstChar);
            } else {
                sMap.put(firstChar, firstCount);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new Program438().findAnagrams("aa", "bb")));
    }
}
