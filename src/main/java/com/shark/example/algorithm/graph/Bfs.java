package com.shark.example.algorithm.graph;

import java.util.*;

public class Bfs {
    public void start(Map<String, List<String>> graph, String startNode) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        // 將起始節點加入佇列並標記為已訪問
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();
            System.out.println("current node = " + currentNode);
            // 遍歷相鄰節點
            for (String neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor)) {
                    // 將未訪問的相鄰節點加入佇列並標記為已訪問
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
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

        Bfs bfs = new Bfs();
        bfs.start(graph, "A");
    }
}
