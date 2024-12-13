package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program92 {


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) {
            return head;
        }

        ListNode current = head;
        ListNode startReverseNode = null;
        ListNode preReverseNode = null;
        ListNode leftNode = null;
        ListNode rightNode = null;
        int index = 0;
        while (current != null) {
            if(index == left - 2) {
                startReverseNode = current;
                current = current.next;
            } else if( index >= left -1 && index <= right -1) {
                if(index == left -1) {
                    leftNode = current;
                }
                if(index == right -1) {
                    rightNode = current;
                    if(startReverseNode != null) {
                        startReverseNode.next = rightNode;
                    }
                }
                ListNode temp = current;
                current = current.next;
                temp.next = preReverseNode;
                preReverseNode = temp;
            } else if(index == right) {
                leftNode.next = current;
                break;
            } else {
                current = current.next;
            }

            index ++;
        }

        if(left == 1) {
            return rightNode;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        System.out.println(new Gson().toJson(new Program92().reverseBetween(head, 1, 2)));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
