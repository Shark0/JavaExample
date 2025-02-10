package com.shark.example.algorithm.leetcode.page5;

public class Program222 {

    public int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int count = 1;
        if(node.left != null) {
            count = count + countNodes(node.left);
        }
        if(node.right != null) {
            count = count + countNodes(node.right);
        }
        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println("count: " + new Program222().countNodes(root));
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
