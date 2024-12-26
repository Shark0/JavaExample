package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Program148 {


    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        while (head != null) {
            priorityQueue.add(head);
            head = head.next;
        }

        ListNode result = null;
        ListNode current = null;
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            if (result == null) {
                result = node;
            }
            if(current != null) {
                current.next = node;
            }
            current = node;
            current.next = null;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        System.out.println(new Gson().toJson(new Program148().sortList(head)));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
