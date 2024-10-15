package com.shark.example.algorithm.leetcode.page43;

import java.util.ArrayList;
import java.util.List;

public class Program2130 {

    public int pairSum(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode current = head; current != null; current = current.next) {
            values.add(current.val);
        }
        int max = 0;

        for (int i = 0; i < values.size() / 2; i++) {
            int temp = values.get(i) + values.get(values.size()  - 1 - i);
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        int result = new Program2130().pairSum(head);
        System.out.println("result: " + result);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
