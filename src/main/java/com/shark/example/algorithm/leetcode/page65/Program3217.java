package com.shark.example.algorithm.leetcode.page65;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class Program3217 {

    public ListNode modifiedList(int[] nums, ListNode head) {
        if (nums == null || nums.length == 0 || head == null) {
            return head;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int i : nums) {
            numSet.add(i);
        }
        ListNode parent = null;
        ListNode current = head;
        while (current!= null) {
            if (parent == null) {
                System.out.println("current: " + current.val);
            } else {
                System.out.println("parent: " + parent.val + ", current: " + current.val);
            }

            if (numSet.contains(current.val)) {
                if (parent == null) {
                    head = current.next;
                } else {
                    parent.next = current.next;
                }
            } else {
                parent = current;
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Program3217 program3217 = new Program3217();
        int[] num;
        ListNode head;
//        num = new int[]{1, 2, 3};
//        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        num = new int[]{9, 2, 5};
        head = new ListNode(2, new ListNode(10, new ListNode(9)));
        ListNode result = program3217.modifiedList(num, head);
        System.out.println(new Gson().toJson(result));
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
