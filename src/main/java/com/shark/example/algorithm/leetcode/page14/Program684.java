package com.shark.example.algorithm.leetcode.page14;

import com.google.gson.Gson;

import java.util.*;

public class Program684 {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> nodeNodeSetMap = new HashMap<>();
        for (int[] edge : edges) {
            int value1 = edge[0];
            int value2 = edge[1];
            Set<Integer> value1Set = nodeNodeSetMap.getOrDefault(value1, new HashSet<>());
            Set<Integer> value2Set = nodeNodeSetMap.getOrDefault(value2, new HashSet<>());
            if(value1Set.contains(value2) || value2Set.contains(value1)) {
                return new int[]{value1, value2};
            }
            System.out.println("value1: " + value1 + " value1Set: " + new Gson().toJson(value1Set));
            for (int value : value1Set) {
                if(value == value1) {
                    continue;
                }
                Set<Integer> valueSet = nodeNodeSetMap.getOrDefault(value, new HashSet<>());
                System.out.println("value: " + value + " valueSet: " + new Gson().toJson(valueSet));
                valueSet.addAll(value2Set);
                valueSet.add(value2);

            }
            value1Set.addAll(value2Set);
            nodeNodeSetMap.put(value1, value1Set);

            System.out.println("value2: " + value2 + " value2Set: " + new Gson().toJson(value2Set));
            for (int value : value2Set) {
                if(value == value2) {
                    continue;
                }
                Set<Integer> valueSet = nodeNodeSetMap.getOrDefault(value, new HashSet<>());
                System.out.println("value: " + value + " valueSet: " + new Gson().toJson(valueSet));
                valueSet.addAll(value1Set);
                valueSet.add(value1);
            }
            value2Set.addAll(value1Set);
            nodeNodeSetMap.put(value2, value2Set);
            value1Set.add(value2);
            value2Set.add(value1);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Program684().findRedundantConnection(
                new int[][]{{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}})));
    }
}
