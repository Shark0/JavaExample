package com.shark.example.algorithm.leetcode.page4;

import java.util.HashSet;
import java.util.Set;

public class Program160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode current = headA;
        while (current != null) {
            set.add(current);
            current = current.next;
        }
        current = headB;
        while (current != null) {
            if(set.contains(current)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public static void main(String[] argv) {
        ListNode same = new ListNode(1);
        ListNode nodeA =  new ListNode(0);
        nodeA.next = same;
        ListNode nodeB = new ListNode(0);
        nodeB.next = same;
        Program160 program160 = new Program160();
        ListNode result = program160.getIntersectionNode(nodeA, nodeB);
        System.out.println(result.val);
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
