package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program117 {

    public Node connect(Node root) {
        if(root == null) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Node> newNodes = new ArrayList<>();
            Node current = null;
            for (Node node : nodes) {
                if (node.left != null) {
                    newNodes.add(node.left);
                    if (current != null) {
                        current.next = node.left;
                    }
                    current = node.left;
                }
                if (node.right != null) {
                    newNodes.add(node.right);
                    if (current != null) {
                        current.next = node.right;
                    }
                    current = node.right;
                }
            }
            nodes = newNodes;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        System.out.println(new Gson().toJson(new Program117().connect(root)));
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
