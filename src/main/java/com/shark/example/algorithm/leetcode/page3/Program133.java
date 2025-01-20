package com.shark.example.algorithm.leetcode.page3;

import java.util.*;

public class Program133 {

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        Map<Node, Node> copiedMap = new HashMap<>();
        Node root = new Node(node.val);
        copiedMap.put(node, root);
        List<Node> taskList = new ArrayList<>();
        taskList.add(node);
        while (!taskList.isEmpty()) {
            Node task = taskList.remove(0);
            List<Node> neighborList = task.neighbors;
            if(neighborList == null || neighborList.isEmpty()) {
                continue;
            }
            Node clone = copiedMap.get(task);
            List<Node> cloneNeighbirList = new ArrayList<>();
            for (Node neighbor : neighborList) {
                Node cloneNeighbor = copiedMap.get(neighbor);
                if(cloneNeighbor == null) {
                    cloneNeighbor  = new Node(neighbor.val);
                    copiedMap.put(neighbor, cloneNeighbor);
                    taskList.add(neighbor);
                }
                cloneNeighbirList.add(cloneNeighbor);
            }
            clone.neighbors = cloneNeighbirList;
        }
        return root;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        new Program133().cloneGraph(node1);
    }

    public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
