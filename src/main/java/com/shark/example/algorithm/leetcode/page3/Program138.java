package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Program138 {

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        while (current != null) {
            Node copy = map.get(current);
            if (copy == null) {
                copy = new Node(current.val);
                map.put(current, copy);
            }
            Node copyNext = null;
            if(current.next != null) {
                copyNext = map.get(current.next);
                if (copyNext == null) {
                    copyNext = new Node(current.next.val);
                    map.put(current.next, copyNext);
                }
            }
            Node copyRandom = null;
            if(current.random != null) {
                copyRandom = map.get(current.random);
                if (copyRandom == null) {
                    copyRandom = new Node(current.random.val);
                    map.put(current.random, copyRandom);
                }
            }
            copy.next = copyNext;
            copy.random = copyRandom;

            current = current.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(0);
        head.next.next.next = new Node(-4);
        head.next.next.next.next = head.next.next;
        System.out.println(new Gson().toJson(new Program138().copyRandomList(head)));
    }

    static class Node {
        int val;
        Node next;
        Node random;

        Node(int x) {
            val = x;
            next = null;
            random = null;
        }
    }
}
