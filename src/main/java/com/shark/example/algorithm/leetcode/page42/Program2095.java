package com.shark.example.algorithm.leetcode.page42;

import com.google.gson.Gson;

public class Program2095 {

    public ListNode deleteMiddle(ListNode head) {
        int count = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        int middleIndex = count / 2;
        if (middleIndex == 0) {
            return null;
        }
        System.out.println("count: " + count);
        System.out.println("middleIndex: " + middleIndex);
        int currentIndex = 0;
        currentNode = head;
        while (currentIndex < middleIndex) {
            if (currentIndex == middleIndex - 1) {
                currentNode.next = currentNode.next.next;
                break;
            }
            currentNode = currentNode.next;
            currentIndex ++;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        Program2095 program2095 = new Program2095();
        System.out.println("result: " + new Gson().toJson(program2095.deleteMiddle(head)));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
