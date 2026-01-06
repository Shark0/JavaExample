package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

public class Program99 {

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

    private boolean isSwitched = false;

    public void recoverTree(TreeNode current) {
        if(isSwitched) {
            return;
        }
        System.out.println("currentValue: " + current.val);
        //find the min node from right
        TreeNode rightMinNode = findMinNode(current.right);
        //find the max node from left
        TreeNode leftMaxNode = findMaxNode(current.left);
        //switch
        if(rightMinNode != null && leftMaxNode != null && rightMinNode.val < leftMaxNode.val) {
            System.out.println("rightMinNode.val > leftMaxNode.val");
            int minValue = rightMinNode.val;
            rightMinNode.val = leftMaxNode.val;
            leftMaxNode.val = minValue;
            isSwitched = true;
        } else if(rightMinNode != null && rightMinNode.val < current.val) {
            System.out.println("rightMinNode.val > current.val");
            int minValue = rightMinNode.val;
            rightMinNode.val = current.val;
            current.val = minValue;
            isSwitched = true;
        } else if(leftMaxNode != null && leftMaxNode.val > current.val) {
            System.out.println("leftMaxNode.val > current.val");
            int maxValue = leftMaxNode.val;
            leftMaxNode.val = current.val;
            current.val = maxValue;
            isSwitched = true;
        }
        if(current.right != null) {
            recoverTree(current.right);
        }
        if(current.left != null) {
            recoverTree(current.left);
        }
    }

    public TreeNode findMinNode(TreeNode current) {
        if(current == null) {
            return null;
        }
        TreeNode minNode = current;
        TreeNode rightMinNode = findMinNode(current.right);
        if(rightMinNode != null && rightMinNode.val < minNode.val) {
            minNode = rightMinNode;
        }
        TreeNode leftMinNode = findMinNode(current.left);
        if(leftMinNode != null && leftMinNode.val < minNode.val) {
            minNode = leftMinNode;
        }
        return minNode;
    }

    public TreeNode findMaxNode(TreeNode current) {
        if(current == null) {
            return null;
        }
        TreeNode maxNode = current;
        TreeNode rightMaxNode = findMaxNode(current.right);
        if(rightMaxNode != null && rightMaxNode.val > maxNode.val) {
            maxNode = rightMaxNode;
        }
        TreeNode leftMaxNode = findMaxNode(current.left);
        if(leftMaxNode != null && leftMaxNode.val > maxNode.val) {
            maxNode = leftMaxNode;
        }
        return maxNode;
    }

    public static void main(String[] argv) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        new Program99().recoverTree(root);
        System.out.println("result: " + new Gson().toJson(root));
    }
}
