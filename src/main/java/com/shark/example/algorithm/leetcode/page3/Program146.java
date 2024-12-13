package com.shark.example.algorithm.leetcode.page3;

import java.util.HashMap;
import java.util.Map;

public class Program146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.printHead();
        cache.put(2, 2);
        cache.printHead();
        cache.put(3, 3);
        cache.printHead();
        cache.put(4, 4);
        cache.printHead();
        cache.put(2, 2);
        cache.printHead();
        cache.get(3);
        cache.printHead();
    }

    static class LRUCache {

        private final int capacity;
        private final Node headNode;
        private final Node tailNode;
        private final Map<Integer, Node> keyNodeMap;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.keyNodeMap = new HashMap<>();
            headNode = new Node();
            tailNode = new Node();
            headNode.next = tailNode;
            tailNode.preview = headNode;
        }

        public int get(int key) {
            Node node = keyNodeMap.get(key);
            if (node == null) {
                return -1;
            }
            moveNodeToTail(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = keyNodeMap.get(key);
            if (node == null) {
                node = new Node();
                node.key = key;
                node.value = value;
                if(keyNodeMap.size() == capacity) {
                    Node firstNode = headNode.next;
                    keyNodeMap.remove(firstNode.key);
                    headNode.next = headNode.next.next;
                    headNode.next.preview = headNode;
                }
                node.preview = tailNode.preview;
                node.next = tailNode;
                node.preview.next = node;
                node.next.preview = node;
                keyNodeMap.put(key, node);
            } else {
                node.value = value;
                moveNodeToTail(node);
            }
        }

        private void moveNodeToTail(Node node) {
            node.preview.next = node.next;
            node.next.preview = node.preview;
            node.preview = tailNode.preview;
            node.next = tailNode;
            node.preview.next = node;
            node.next.preview = node;
        }

        public void printHead() {
            StringBuilder stringBuilder = new StringBuilder();
            Node temp = headNode;
            while (temp != null) {
                stringBuilder.append(temp.key).append(" -> ");
                temp = temp.next;
            }
            stringBuilder.append("null");
            System.out.println(stringBuilder);
        }

        class Node {
            int key;
            int value;
            Node preview;
            Node next;
        }
    }
}
