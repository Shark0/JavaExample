package com.shark.example.algorithm.leetcode.page2;

public class Program100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    public static void main(String[] args) {
        System.out.println(new Program100().isSameTree(null, null));
    }

    public class TreeNode {
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
