package com.shark.example.algorithm.leetcode.page30;

import java.util.*;

public class Program1466 {

    public int minReorder(int n, int[][] connections) {
        Set<Integer> checkedNodeSet = new HashSet<>();
        HashMap<Integer, List<int[]>> nodeConnectionMap = new HashMap<>();
        for (int[] connection : connections) {
            int node1 = connection[0];
            List<int[]> node1Connections = nodeConnectionMap.get(node1);
            if(node1Connections == null) {
                node1Connections = new ArrayList<>();
            }
            node1Connections.add(connection);
            nodeConnectionMap.put(node1, node1Connections);
            int node2 = connection[1];
            List<int[]> node2Connections = nodeConnectionMap.get(node2);
            if(node2Connections == null) {
                node2Connections = new ArrayList<>();
            }
            node2Connections.add(connection);
            nodeConnectionMap.put(node2, node2Connections);
        }
        return dfs(0, nodeConnectionMap, checkedNodeSet);
    }

    public int dfs(int node, HashMap<Integer, List<int[]>> nodeConnectionMap,
                   Set<Integer> checkedNodeSet) {
        checkedNodeSet.add(node);
        int result = 0;
        List<int[]> nodeConnectionList = nodeConnectionMap.get(node);
        List<Integer> checkNodeList = new ArrayList<>();
        for (int [] connection : nodeConnectionList) {
            if (connection[0] == node) {
                if(!checkedNodeSet.contains(connection[1])) {
                    result++;
                    checkNodeList.add(connection[1]);
                }
            }
            if (connection[1] == node) {
                if(!checkedNodeSet.contains(connection[0])) {
                    checkNodeList.add(connection[0]);
                }

            }
        }
        for(int checkNode : checkNodeList) {
            result = result + dfs(checkNode, nodeConnectionMap, checkedNodeSet);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] connections = new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println("result: " +  new Program1466().minReorder(6, connections));

    }
}
