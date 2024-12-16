package com.shark.example.algorithm.leetcode.page3;

public class Program112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        int nextTargetSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return nextTargetSum == 0;
        }
        return hasPathSum(root.left, nextTargetSum) || hasPathSum(root.right, nextTargetSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        System.out.println(new Program112().hasPathSum(root, 13));
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
