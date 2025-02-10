package com.shark.example.algorithm.leetcode.page3;

public class Program129 {

    public int sumNumbers(TreeNode node) {
        if(node == null) return 0;

        if(node.left == null && node.right == null) {
            return node.val;
        }
        int leftSum = sumNumbers(node.left, node.val);
        int rightSum = sumNumbers(node.right, node.val);
        return leftSum + rightSum;
    }

    public int sumNumbers(TreeNode node, int parentValue) {
        if(node == null) return 0;
        int newValue = Integer.parseInt(parentValue + String.valueOf(node.val));
        if(node.left == null && node.right == null) {
            return newValue;
        }
        return sumNumbers(node.left, newValue) + sumNumbers(node.right, newValue);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(9);
//        node.left = new TreeNode(2);
//        node.right = new TreeNode(3);
        System.out.println("sum: " + new Program129().sumNumbers(node));
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

