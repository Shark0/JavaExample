package com.shark.example.algorithm.leetcode.page5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program234 {

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        int count = list.size();
        for (int i = 0; i < count / 2; i++) {
            if (!Objects.equals(list.get(i), list.get(count - i - 1))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(1);
        Program234 program234 = new Program234();
        System.out.println(program234.isPalindrome(head));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
