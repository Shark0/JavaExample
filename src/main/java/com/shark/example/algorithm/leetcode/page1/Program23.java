package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode head = null;
        ListNode tail = null;
        while (true) {
            Integer index = null;
            Integer value = null;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if(node != null) {
                    if(value == null || node.val < value) {
                        value = node.val;
                        index = i;
                    }
                }
            }
            if(value == null) {
                return head;
            }
            ListNode node = new ListNode(value);
            if(head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            lists[index] = lists[index].next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(3, new ListNode(5, null)));
        ListNode listNode2 = new ListNode(2, new ListNode(4, new ListNode(6, null)));;
        Program23 mergeKLists = new Program23();
        System.out.println(new Gson().toJson(mergeKLists.mergeKLists(new ListNode[] {listNode1, listNode2})));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
