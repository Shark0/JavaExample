package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Tail = l1;
        ListNode l2Tail = l2;
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (l1Tail != null || l2Tail != null) {
            int l1Value = 0;
            if (l1Tail != null) {
                l1Value = l1Tail.val;
                l1Tail = l1Tail.next;
            }
            int l2Value = 0;
            if(l2Tail != null) {
                l2Value = l2Tail.val;
                l2Tail = l2Tail.next;
            }
            int value = carry + l1Value + l2Value;
            carry = value / 10;
            value = value % 10;

            ListNode node = new ListNode(value);
            if (head == null) {
                head = node;
            }
            if(tail != null) {
                tail.next = node;
            }
            tail = node;
        }

        if(carry != 0) {
            ListNode node = new ListNode(carry);
            tail.next = node;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        Program2 program2 = new Program2();
        ListNode result = program2.addTwoNumbers(l1, l2);
        System.out.println("result: " + new Gson().toJson(result));
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
