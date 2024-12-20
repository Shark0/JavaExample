package com.shark.example.algorithm.leetcode.page1;

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

    public static void compare(String value1, String value2) {
        value1 = value1.replace("[","").replace("]","").replace("\"", "");
        System.out.println(value1);
        value2 = value2.replace("[","").replace("]","").replace("\"", "");
        System.out.println(value2);

        String[] value1Keys = value1.split(",");
        String[] value2Keys = value2.split(",");

        Set<String> value1KeySet = new HashSet<>(Arrays.asList(value1Keys));
        Set<String> value2KeySet = new HashSet<>(Arrays.asList(value2Keys));
        System.out.println("value1KeySet: " + new Gson().toJson(value1KeySet));
        System.out.println("value2KeySet: " + new Gson().toJson(value2KeySet));
        for(String key : value1KeySet) {
            if(!value2KeySet.contains(key)) {
                System.out.println("value2 not contains " + key);
            }
        }
        for(String key : value2KeySet) {
            if(!value1KeySet.contains(key)) {
                System.out.println("value1 not contains " + key);
            }
        }
    }

    public static void main(String[] args) {
        String[] input = {"ddddddddddg", "dgggggggggg"};
        Program49 program49 = new Program49();
        List<List<String>> result = program49.groupAnagrams(input);
        System.out.println("result: " + new Gson().toJson(result));
    }
}
