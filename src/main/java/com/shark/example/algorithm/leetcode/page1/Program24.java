package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode result = null;
        ListNode currentFather = null;
        while (true) {
            ListNode next = current.next;
            ListNode tail = next.next;
            if(result == null) {
                result = next;
            }
            current.next = tail;
            next.next = current;
            current = next;
            if(currentFather != null) {
                currentFather.next = current;
            }
            //next stage
            currentFather = current.next;
            current = tail;
            if(current == null || current.next == null) {
                return result;
            }
        }
    }

    public static void main(String[] args) {
        Program24 swapPairs = new Program24();
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(new Gson().toJson(swapPairs.swapPairs(node)));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
