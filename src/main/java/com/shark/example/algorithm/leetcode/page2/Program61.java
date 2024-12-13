package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Program61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) {
            return head;
        }
        int index = 0;
        ListNode current = head;
        Map<Integer, ListNode> map = new HashMap<>();
        while (current != null) {
            map.put(index, current);
            current = current.next;
            index++; //total count
        }
        if(index == 1) {
            return head;
        }
        k = k % index;
        if(k == 0) {
            return head;
        }
        map.get(index - 1).next = map.get(0);
        int headIndex = index - k;
        map.get(headIndex - 1).next = null;
        return map.get(headIndex);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(new Gson().toJson(new Program61().rotateRight(head, 3)));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
