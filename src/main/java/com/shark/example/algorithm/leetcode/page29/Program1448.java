package com.shark.example.algorithm.leetcode.page29;

public class Program1448 {
    public int goodNodes(TreeNode root) {
        int result = 1;
        if (root.left != null) {
            result = result + calculate(root.left, root.val);
        }
        if (root.right != null) {
            result = result + calculate(root.right, root.val);
        }
        return result;
    }

    public int calculate(TreeNode node, int value) {
        int result = node.val >= value ? 1 : 0;
        if(node.left != null) {
            result = result + calculate(node.left, Math.max(node.val, value));
        }
        if(node.right != null) {
            result = result + calculate(node.right, Math.max(node.val, value));
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        System.out.println("result: " + new Program1448().goodNodes(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
