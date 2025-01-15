package com.shark.example.algorithm.leetcode.page5;

import java.util.ArrayList;
import java.util.List;

public class Program230 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> valueSortList = new ArrayList<>();
        sortValueToList(root, valueSortList, k);
        if(valueSortList.size() < k) {
            return -1;
        } else {
            return valueSortList.get(k - 1);
        }
    }

    private void sortValueToList(TreeNode node, List<Integer> valueSortList, int k) {
        if(node == null) {
            return;
        }
        sortValueToList(node.left, valueSortList, k);
        if(valueSortList.size() >= k) {
            return;
        }
        valueSortList.add(node.val);
        if(valueSortList.size() >= k) {
            return;
        }
        sortValueToList(node.right, valueSortList, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(new Program230().kthSmallest(root, 2));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
