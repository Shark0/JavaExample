package com.shark.example.algorithm.graph;

import java.util.*;

public class Dfs {
    public void start(Map<String, List<String>> graph, Set<String> visited, String currentNode) {
        // 訪問節點
        System.out.println("current node = " + currentNode);
        visited.add(currentNode);

        // 遞迴訪問相鄰節點
        for (String neighbor : graph.get(currentNode)) {
            if (!visited.contains(neighbor)) {
                start(graph, visited, neighbor);
            }
        }
    }

    public static void main(String[] argv) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("A", "D", "E"));
        graph.put("C", List.of("A", "F", "G"));
        graph.put("D", List.of("B"));
        graph.put("E", List.of("B", "H"));
        graph.put("F", List.of("C"));
        graph.put("G", List.of("C"));
        graph.put("H", List.of("E"));

        Dfs dfs = new Dfs();
        Set<String> visited = new HashSet<>();
        dfs.start(graph, visited, "A");
    }
}
