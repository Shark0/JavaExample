package com.shark.example.algorithm.leetcode.page1.program19;

import com.google.gson.Gson;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n == 0) {
            return head;
        }
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        current = head;
        int i = 0;
        if(n == count ) {
            return head.next;
        } else {
            while (i < count - n) {
                if(i == (count - n - 1)) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
                i ++;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode listNode = new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(new Gson().toJson(removeNthFromEnd.removeNthFromEnd(listNode, 2)));
    }

}