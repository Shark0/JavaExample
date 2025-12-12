package com.shark.example.algorithm.leetcode.page2;

public class Program83 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode previous = head;
        ListNode current = head.next;
        while(current != null) {
            if(previous.val == current.val) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return head;
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
