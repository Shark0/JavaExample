package com.shark.example.algorithm.leetcode.page1;

import com.google.gson.Gson;

public class Program23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    public ListNode divideAndConquer(ListNode[] lists, int start, int end) {
        if(start == end) {
            return lists[start];
        }
        ListNode left = divideAndConquer(lists, start, (start + end) / 2);
        ListNode right = divideAndConquer(lists, (start + end) / 2 + 1, end);
        return merge(left, right);
    }

    public ListNode merge(ListNode listNode1, ListNode listNode2) {
        if(listNode1 == null) {
            return listNode2;
        }
        if(listNode2 == null) {
            return listNode1;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val <= listNode2.val) {
                current.next = listNode1;   // 把較小的節點接到新鏈表後
                listNode1 = listNode1.next; // 移動 list1 的指標
            } else {
                current.next = listNode2;
                listNode2 = listNode2.next; // 移動 list2 的指標
            }
            current = current.next; // 移動新鏈表的指標
        }

        if (listNode1 != null) {
            current.next = listNode1;
        } else {
            current.next = listNode2;
        }

        return dummy.next;
    }


    public ListNode mergeKListsBackup(ListNode[] lists) {
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
