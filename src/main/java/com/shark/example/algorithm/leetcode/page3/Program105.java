package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Program105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //pre-order: right or down
        TreeNode root = new TreeNode(preorder[0]);
        Map<Integer, Integer> indoorIndexMap = generateIndoorIndexMap(inorder);

        for(int i = 1; i < preorder.length; i++) {
            int preOrder = preorder[i];
            TreeNode node = root;
            while (true) {
                int preOrderIndoorIndex = indoorIndexMap.get(preOrder);
                int nodeValueIndoorIndex = indoorIndexMap.get(node.val);
                boolean isLeft = preOrderIndoorIndex <= nodeValueIndoorIndex;
                if(isLeft) {
                    TreeNode leftNode = node.left;
                    if(leftNode == null) {
                        leftNode = new TreeNode(preOrder);
                        node.left = leftNode;
                        break;
                    } else {
                        node = leftNode;
                    }
                } else {
                    TreeNode rightNode = node.right;
                    if(rightNode == null) {
                        rightNode = new TreeNode(preOrder);
                        node.right = rightNode;
                        break;
                    } else {
                        node = rightNode;
                    }
                }
            }
        }

        return root;
    }

    public Map<Integer, Integer> generateIndoorIndexMap(int[] indoors) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < indoors.length; i++) {
            map.put(indoors[i], i);
        }
        return map;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println("tree node: " + new Gson().toJson(new Program105().buildTree(preorder, inorder)));
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
