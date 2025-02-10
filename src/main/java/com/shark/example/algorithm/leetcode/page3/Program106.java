package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Program106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderIndexMap = generateInOrderIndexMap(inorder);
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);

        for (int i = postorder.length - 2; i >= 0; i--) {
            // down or left
            int postOrderValue = postorder[i];
            TreeNode postOrderNode = new TreeNode(postOrderValue);
            int postOrderValueInInOrderIndex = inOrderIndexMap.get(postOrderValue);
            TreeNode current = root;
            while (true) {
                int currentValue = current.val;
                int currentValueInInOrderIndex = inOrderIndexMap.get(currentValue);
                if (postOrderValueInInOrderIndex > currentValueInInOrderIndex) {
                    if (current.right == null) {
                        current.right = postOrderNode;
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    if (current.left == null) {
                        current.left = postOrderNode;
                        break;
                    } else {
                        current = current.left;
                    }
                }
            }
        }
        return root;
    }

    public Map<Integer, Integer> generateInOrderIndexMap(int[] inOrders) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrders.length; i++) {
            map.put(inOrders[i], i);
        }
        return map;
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7}; //parent or right
        int[] postorder = {9, 15, 7, 20, 3};
        Program106 program106 = new Program106();
        TreeNode root = program106.buildTree(inorder, postorder);
        System.out.println(new Gson().toJson(root));
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
