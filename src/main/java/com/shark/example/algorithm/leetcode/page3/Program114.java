package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

public class Program114 {

    public void flatten(TreeNode root) {
        merge(root);
    }

    private TreeNode merge(TreeNode node) {
        if(node == null) {
            return null;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        if(left == null && right == null) {
            return node;
        }
        TreeNode leftRight = merge(left);
        TreeNode rightRight = merge(right);
        if (leftRight != null) {
            node.left = null;
            node.right = left;
            leftRight.right = right;
        }
        if (rightRight != null) {
            return rightRight;
        }
        return leftRight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        new Program114().flatten(root);
        System.out.println(new Gson().toJson(root));
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
