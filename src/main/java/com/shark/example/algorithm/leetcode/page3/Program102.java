package com.shark.example.algorithm.leetcode.page3;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class Program102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null) {
            return results;
        }
        List<TreeNode> currentNodeList = List.of(root);
        while (!currentNodeList.isEmpty()) {
            List<TreeNode> tempNodeList = new ArrayList<>();
            List<Integer> resultList = new ArrayList<>();
            for (TreeNode node : currentNodeList) {
                resultList.add(node.val);
                if(node.left != null) {
                    tempNodeList.add(node.left);
                }
                if(node.right != null) {
                    tempNodeList.add(node.right);
                }
            }
            results.add(resultList);
            currentNodeList = tempNodeList;
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
        System.out.println(new Gson().toJson(new Program102().levelOrder(root)));
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
