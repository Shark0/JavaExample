package com.shark.example.algorithm.leetcode.page1.program37;

import com.google.gson.Gson;

import java.util.*;

public class Program37Helper {

    public String translate(String value) {
        value = value.replaceAll("\\[", "{");
        value = value.replaceAll("]", "}");
        value = value.replaceAll("\"", "'");
        return value;
    }

    public List<Set<Integer>> findRepeatSetList(List<Set<Integer>> list) {
        List<Set<Integer>> result = new ArrayList<>();
        Map<String, Set<Integer>> newSetMap = new HashMap<>();
        for (Set<Integer> set : list) {
            Map<String, Set<Integer>> tempNewSetMap = new HashMap<>();
            for(String key: newSetMap.keySet()) {
                Set<Integer> set2 = newSetMap.get(key);
                int containCount = 0;
                for(Integer value: set2) {
                    if(set.contains(value)) {
                        containCount++;
                    }
                }
                if(containCount > 0 && containCount != set.size()) {
                    Set<Integer> newSet = new HashSet<>(set);
                    newSet.addAll(set2);
                    String newSetKey = newSet.toString();
                    tempNewSetMap.put(newSetKey, newSet);
                }
            }
            newSetMap.putAll(tempNewSetMap);
            String key = set.toString();
            if (!newSetMap.containsKey(key)) {
                newSetMap.put(key, set);
            }
        }
        System.out.println("newSetMap: " + new Gson().toJson(newSetMap));
        Map<String, Integer> keyCountMap = new HashMap<>();
        for(String key: newSetMap.keySet())  {
            Integer count = keyCountMap.computeIfAbsent(key, k -> 0);
            Set<Integer> newSet = newSetMap.get(key);
            System.out.println("newSet: " + new Gson().toJson(newSet));
            for(Set<Integer> set: list) {
                System.out.println("set: " + new Gson().toJson(set));
                if(newSet.containsAll(set)) {
                    System.out.println("containsAll: " + new Gson().toJson(set));
                    count++;
                }
            }
            keyCountMap.put(key, count);
        }
        System.out.println("keyCountMap: " + new Gson().toJson(keyCountMap));
        for(String key: newSetMap.keySet())  {
            Integer count = keyCountMap.get(key);
            Set<Integer> newSet = newSetMap.get(key);
            if(count == newSet.size()) {
                result.add(newSet);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Program37Helper program37Helper = new Program37Helper();
//        System.out.println(program37Helper.translate("[[\".\",\".\",\"9\",\"7\",\"4\",\"8\",\".\",\".\",\".\"],[\"7\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\",\"2\",\".\",\"1\",\".\",\"9\",\".\",\".\",\".\"],[\".\",\".\",\"7\",\".\",\".\",\".\",\"2\",\"4\",\".\"],[\".\",\"6\",\"4\",\".\",\"1\",\".\",\"5\",\"9\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\"3\",\".\",\".\"],[\".\",\".\",\".\",\"8\",\".\",\"3\",\".\",\"2\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\"6\"],[\".\",\".\",\".\",\"2\",\"7\",\"5\",\"9\",\".\",\".\"]]"));


        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(3);
        set1.add(5);
        set1.add(6);
        Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(3);
        set2.add(5);
        Set<Integer> set3 = new HashSet<>();
        set3.add(1);
        set3.add(6);
        Set<Integer> set4 = new HashSet<>();
        set4.add(1);
        set4.add(3);
        set4.add(5);
        set4.add(6);

        List<Set<Integer>> input = List.of(set1, set2, set3, set4);
        System.out.println("input: " + gson.toJson(input));
        List<Set<Integer>> setResult =
                program37Helper.findRepeatSetList(input);
        System.out.println("set result: " + gson.toJson(setResult));
    }
}
