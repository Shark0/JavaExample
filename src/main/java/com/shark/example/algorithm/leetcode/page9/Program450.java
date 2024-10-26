package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

public class Program450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if(key == root.val) {
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }
            TreeNode rightMinNode = null;
            TreeNode rightNode = root.right;
            while(rightNode != null) {
                rightMinNode = rightNode;
                rightNode = rightNode.left;
            }
            rightMinNode.left = root.left;
            return root.right;
        } else if(key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("result: " + new Gson().toJson(new Program450().deleteNode(root, 6)));
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
