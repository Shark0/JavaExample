package com.shark.example.algorithm.leetcode.page3;

public class Program124 {

    public int maxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int[] result = calculateNodeMaxPathSum(node);
        System.out.println(result[0] + " " + result[1]);
        return Math.max(result[0], result[1]);
    }

    public int[] calculateNodeMaxPathSum(TreeNode node) {
        //results[] 0: max path 1: max sub result
        if (node == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        int maxPath = node.val;
        int maxSubResult = Integer.MIN_VALUE;
        if (node.left == null && node.right == null) {
            return new int[]{node.val, node.val};
        }

        int[] leftResults = calculateNodeMaxPathSum(node.left);
        int maxLeftPath = leftResults[0];
        int maxLeft = leftResults[1];

        int[] rightResults = calculateNodeMaxPathSum(node.right);
        int maxRightPath = rightResults[0];
        int maxRight = rightResults[1];

        maxPath = Math.max(maxPath, maxPath + Math.max(maxLeftPath, maxRightPath));
        if (node.left != null && node.right != null) {
            maxSubResult = node.val + maxLeftPath + maxRightPath;
        }
        maxSubResult = Math.max(Math.max(Math.max(maxSubResult, maxLeft), maxRight), maxPath);
        System.out.println("node value: " + node.val + ", maxPath: " + maxPath + ", maxSubResult: " + maxSubResult);
        System.out.println("left: " + leftResults[0] + ", " + leftResults[1]);
        System.out.println("right: " + rightResults[0] + ", " + rightResults[1]);
        return new int[]{maxPath, maxSubResult};
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(-1);
        node.left = new TreeNode(5);
//        node.left = new TreeNode(9);
//        node.right = new TreeNode(20);
//        node.right.left = new TreeNode(15);
//        node.right.right = new TreeNode(7);
        System.out.println(new Program124().maxPathSum(node));
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
