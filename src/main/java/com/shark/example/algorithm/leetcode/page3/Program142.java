package com.shark.example.algorithm.leetcode.page3;

import java.util.HashSet;
import java.util.Set;

public class Program142 {

    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode current = head;
        while(current.next != null) {
            current = current.next;
            if(set.contains(current)) {
                return current;
            }
            set.add(current);
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        ListNode result = new Program142().detectCycle(head);
        System.out.println(result.val);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
