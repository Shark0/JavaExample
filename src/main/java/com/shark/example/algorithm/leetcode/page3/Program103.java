package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null) {
            return results;
        }
        List<TreeNode> currentNodeList = List.of(root);
        int level = 0;
        while (!currentNodeList.isEmpty()) {
            List<TreeNode> tempNodeList = new ArrayList<>();
            List<Integer> resultList = new ArrayList<>();
            int sort = level %2;
            for (TreeNode node : currentNodeList) {
                if(sort == 0) {
                    resultList.add(node.val);
                } else {
                    resultList.add(0, node.val);
                }
                if(node.left != null) {
                    tempNodeList.add(node.left);
                }
                if(node.right != null) {
                    tempNodeList.add(node.right);
                }
            }
            results.add(resultList);
            currentNodeList = tempNodeList;
            level ++;
        }
        return results;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(new Gson().toJson(new Program103().zigzagLevelOrder(root)));
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
