package com.shark.example.algorithm.leetcode.page11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Program530 {

    public int getMinimumDifference(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        List<Integer> valueList = new ArrayList<>();
        while (!nodeList.isEmpty()) {
            List<TreeNode> tempList = new ArrayList<>();
            for (TreeNode node : nodeList) {
                int value = node.val;
                valueList.add(value);
                if(node.left != null) {
                    tempList.add(node.left);
                }
                if(node.right != null) {
                    tempList.add(node.right);
                }
            }
            nodeList = tempList;
        }
        if(valueList.size() == 1) {
            return valueList.get(0);
        }
        valueList.sort(Integer::compareTo);
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < valueList.size(); i++) {
            int temp = Math.abs(valueList.get(i - 1) - valueList.get(i));
            if(temp < result) {
                result = temp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(5);
        System.out.println(new Program530().getMinimumDifference(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
