package com.shark.example.algorithm.leetcode.page28;

public class Program1372 {

    public int longestZigZag(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftResult = 0;
        if(root.left != null) {
            leftResult = calculateMaxZigZag(root.left, 0, 0);
        }
        int rightResult = 0;
        if(root.right != null) {
            rightResult = calculateMaxZigZag(root.right, 1, 0);
        }
        System.out.println("longestZigZag leftResult = " + leftResult + ", rightResult = " + rightResult);
        return Math.max(leftResult, rightResult);
    }

    public int calculateMaxZigZag(TreeNode node, int direction, int parentCount) {
        System.out.println("calculateMaxZigZag start direction = " + direction + ", parentCount = " + parentCount);
        int leftResult = 0;
        int rightResult = 0;
        if(direction == 0) {
            //left
            if(node.left != null) {
                leftResult = calculateMaxZigZag(node.left, 0, 0);
            }
            if(node.right != null) {
                rightResult = calculateMaxZigZag(node.right, 1, parentCount + 1);
            } else {
                rightResult = parentCount + 1;
            }
        } else {
            //right
            if(node.left != null) {
                leftResult = calculateMaxZigZag(node.left, 0, parentCount + 1);
            } else {
                leftResult = parentCount + 1;
            }
            if(node.right != null) {
                rightResult = calculateMaxZigZag(node.right, 1, 0);
            }
        }
        System.out.println("calculateMaxZigZag end leftResult = " + leftResult + ", rightResult = " + rightResult);

        return Math.max(leftResult, rightResult);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        root.right.right.left = new TreeNode(1);
        root.right.right.left.right = new TreeNode(1);
        root.right.right.left.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(1);

        System.out.println("result = " + new Program1372().longestZigZag(root));
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
