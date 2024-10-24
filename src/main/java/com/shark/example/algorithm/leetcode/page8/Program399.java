package com.shark.example.algorithm.leetcode.page8;

import com.google.gson.Gson;

import java.util.*;

public class Program399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> path = equations.get(i);
            String node1 = path.get(0);
            String node2 = path.get(1);
            double value = values[i];
            graph.computeIfAbsent(node1, k -> new HashMap<>()).put(node2, value);
            graph.computeIfAbsent(node2, k -> new HashMap<>()).put(node1, 1 / value);
        }
        System.out.println("graph: " + new Gson().toJson(graph));
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String node1 = query.get(0);
            String node2 = query.get(1);
            Set<String> checkedNodeSet = new HashSet<>();
            Double value = calc(graph, checkedNodeSet, node1, node2);
            if (value == null) {
                results[i] = -1;
            } else {
                results[i] = value;
            }

            System.out.println("node1: " + node1 + ", node2: " + node2 + ", result: " + new Gson().toJson(results[i]));
        }
        return results;
    }

    private Double calc(
            Map<String, Map<String, Double>> graph,
            Set<String> checkedNodeSet, String node1, String node2) {
        checkedNodeSet.add(node1);
        Map<String, Double> node1Path = graph.get(node1);
        if (node1Path == null) {
            return null;
        }
        if (node1.equals(node2)) {
            return 1.0;
        }
        Double value = node1Path.get(node2);
        if (value != null) {
            return value;
        }

        for (String node : node1Path.keySet()) {
            if (checkedNodeSet.contains(node)) {
                continue;
            }
            value = node1Path.get(node);
            Double childValue = calc(graph, checkedNodeSet, node, node2);
            System.out.println("node: " + node + ", node2: " + node2 + ", childValue: " + childValue);
            if (childValue != null) {
                return childValue * value;
            }
        }
        checkedNodeSet.remove(node1);
        return null;
    }

    public static void main(String[] args) {
        List<List<String>> equations =
                List.of(List.of("x1", "x2"), List.of("x2", "x3"), List.of("x3", "x4"), List.of("x4", "x5"));
        double[] values = new double[]{3.0, 4.0, 5.0, 6.0};
        List<List<String>> queries =
                List.of(List.of("x1", "x5"), List.of("x5", "x2"));
        System.out.println("result: " + new Gson().toJson(new Program399().calcEquation(equations, values, queries)));
    }
}
