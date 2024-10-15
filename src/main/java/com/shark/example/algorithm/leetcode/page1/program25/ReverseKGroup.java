package com.shark.example.algorithm.leetcode.page1.program25;

import com.google.gson.Gson;

import java.util.Objects;

public class ReverseKGroup {



    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1 || head == null) {
            return head;
        }

        ListNode current = head;
        ListNode result = null;
        ListNode currentFather = null;
        while(true) {
            ListNode[] reverseArray = new ListNode[k];
            for(int i = 0; i < k; i ++) {
                reverseArray[i] = current;
                if(current != null) {
                    current = current.next;
                } else {
                    return Objects.requireNonNullElse(result, head);
                }
            }

            ListNode tail = null;
            for(int i = 0; i < k; i ++) {
                if(i == 0) {
                    tail = reverseArray[k - i - 1].next;
                }
                if( i != k - 1) {
                    reverseArray[k - i - 1].next = reverseArray[k - i - 2];
                } else {
                    reverseArray[k - i - 1].next = tail;
                }
            }
            if(result == null) {
                result = reverseArray[k - 1];
            }
            if(currentFather != null) {
                currentFather.next = reverseArray[k - 1];
            }
            //next stage
            currentFather = reverseArray[0];
            current = reverseArray[0].next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(new Gson().toJson(
                new ReverseKGroup().reverseKGroup(node, 3))
        );
    }
}
