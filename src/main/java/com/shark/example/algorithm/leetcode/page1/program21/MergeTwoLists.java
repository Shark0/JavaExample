package com.shark.example.algorithm.leetcode.page1.program21;


import com.google.gson.Gson;

public class MergeTwoLists {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cuurentL1 = list1;
        ListNode cuurentL2 = list2;
        ListNode firstNode = null;
        ListNode currentNode = null;

        while (cuurentL1 != null || cuurentL2 != null) {
            int value;
            if(cuurentL1 == null || cuurentL2 == null) {
                if(cuurentL1 == null) {
                    value = cuurentL2.val;
                    cuurentL2 = cuurentL2.next;
                } else {
                    value = cuurentL1.val;
                    cuurentL1 = cuurentL1.next;
                }
            } else {
                if(cuurentL1.val < cuurentL2.val) {
                    value = cuurentL1.val;
                    cuurentL1 = cuurentL1.next;
                } else {
                    value = cuurentL2.val;
                    cuurentL2 = cuurentL2.next;
                }
            }
            ListNode listNode = new ListNode(value);
            if(firstNode == null) {
                firstNode = listNode;
                currentNode = listNode;
            } else {
                currentNode.next = listNode;
                currentNode = listNode;
            }
        }
        return firstNode;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(3, new ListNode(5, null)));
        ListNode listNode2 = new ListNode(2, new ListNode(4, new ListNode(6, null)));;
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        System.out.println(new Gson().toJson(mergeTwoLists.mergeTwoLists(listNode1, listNode2)));
    }
}
