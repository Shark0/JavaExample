package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program86 {

    public ListNode partition(ListNode head, int x) {
        ListNode p1Head = null;
        ListNode p1 = null;
        ListNode p2Head = null;
        ListNode p2 = null;
        ListNode current = head;
        while (current != null) {
            if(current.val < x) {
                if(p1 == null) {
                    p1Head = current;
                    p1 = current;
                } else {
                    p1.next = current;
                    p1= current;
                }
            } else {
                if(p2 == null) {
                    p2Head = current;
                    p2 = current;
                } else {
                    p2.next = current;
                    p2 = current;
                }
            }
            current = current.next;
        }
        if(p1 != null) {
            p1.next = p2Head;
        }
        if(p2 != null) {
            p2.next = null;
        }

        return (p1Head != null)? p1Head: p2Head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        System.out.println(new Gson().toJson(new Program86().partition(head, 3)));
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
