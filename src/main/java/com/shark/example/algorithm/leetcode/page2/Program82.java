package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.Objects;

public class Program82 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode tempHead = head;
        ListNode current = head;
        ListNode previewParent = null;
        int duplicatedValue = -101;
        while (current != null) {
            //check duplicated
            boolean isDuplicated = false;
            if (current.val == duplicatedValue) {
                isDuplicated = true;
            } else {
                if (current.next != null && current.val == current.next.val) {
                    isDuplicated = true;
                }
                duplicatedValue = current.val;
            }

            if (isDuplicated) {
                if (Objects.equals(tempHead, current)) {
                    tempHead = null;
                }
                if (previewParent != null) {
                    previewParent.next = null;
                }
            } else {
                if (tempHead == null) {
                    tempHead = current;
                }
                if (previewParent != null) {
                    previewParent.next = current;
                }
                previewParent = current;

            }
            current = current.next;
        }

        return tempHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(5);
        System.out.println(new Gson().toJson(new Program82().deleteDuplicates(head)));
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
