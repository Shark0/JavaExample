package com.shark.example.algorithm.leetcode.page7;

import com.google.gson.Gson;

public class Program328 {

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode odd = null;
        ListNode currentOdd = null;
        ListNode even = null;
        ListNode currentEven = null;
        ListNode current = head;
        int i = 0;
        while(current != null) {
            if(i == 0) {
                if(currentOdd == null) {
                    odd = current;
                    currentOdd = current;
                } else {
                    currentOdd.next = current;
                    currentOdd = current;
                }
            } else {
                if(currentEven == null) {
                    even = current;
                    currentEven = current;
                } else {
                    currentEven.next = current;
                    currentEven = current;
                }
            }
            current = current.next;
            i = (i + 1) % 2;
        }
        if(currentEven != null) {
            currentEven.next = null;
        }
        currentOdd.next = even;
        return odd;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        Program328 program328 = new Program328();
        ListNode result = program328.oddEvenList(head);
        System.out.println("result = " + new Gson().toJson(result));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
