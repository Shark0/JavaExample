package com.shark.example.algorithm.leetcode.page5;

import com.google.gson.Gson;

import java.util.List;

public class Program206 {

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode preview = head;
        ListNode current = head.next;
        while (current != null) {
            ListNode next = current.next;
            current.next = preview;
            preview = current;
            current = next;
        }
        head.next = null;
        return preview;
    }


    public static void main(String[] args) {
        Program206 program206 = new Program206();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("result: " + new Gson().toJson(program206.reverseList(listNode)));
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
