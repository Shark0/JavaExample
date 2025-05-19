package com.shark.example.algorithm.leetcode.page11;

public class Program543 {

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int[] result = generateNodeResult(root);
        return result[0];
    }


    private int[] generateNodeResult(TreeNode node) {
        int[] result = new int[]{0, 0};
        if(node == null) {
            return result;
        }
        int[] leftResult = generateNodeResult(node.left);
        int[] rightResult = generateNodeResult(node.right);
        int leftMax = leftResult[0];
        int leftMaxDepth = leftResult[1];
        int rightMax = rightResult[0];
        int rightMaxDepth = rightResult[1];
        int maxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;

        int childMax = Math.max(leftMax, rightMax);
        int leftRightMax = leftMaxDepth + rightMaxDepth;
        int max = Math.max(childMax, leftRightMax);

        System.out.println("node.val: " + node.val + ", leftMax: " + leftMax + ", letMaxDepth: " + leftMaxDepth
                + ", rightMax: " + rightMax + ", rightMaxDepth: " + rightMaxDepth + ", maxDepth: " + maxDepth
                + ", childMax: " + childMax + ", leftRightMax: " + leftRightMax + ", max: " + max);
        result[0] = max;
        result[1] = maxDepth;
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
        System.out.println(new Program543().diameterOfBinaryTree(root));
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
