package com.shark.example.algorithm.leetcode.page1.program49;

import com.google.gson.Gson;

import java.util.*;

public class Program49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            System.out.println("key: " + key);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] input = {"ddddddddddg", "dgggggggggg"};
        Program49 program49 = new Program49();
        List<List<String>> result = program49.groupAnagrams(input);
        System.out.println("result: " + new Gson().toJson(result));
    }
}
