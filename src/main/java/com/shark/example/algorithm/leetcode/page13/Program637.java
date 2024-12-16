package com.shark.example.algorithm.leetcode.page13;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Program637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<TreeNode> currentLevlNodeList = List.of(root);
        List<Double> result = new ArrayList<>();
        while (!currentLevlNodeList.isEmpty()) {
            double sum = 0;
            int count = 0;
            List<TreeNode> nextLevlNodeList = new ArrayList<>();
            for (TreeNode node : currentLevlNodeList) {
                sum = sum + node.val;
                count++;
                if (node.left != null) {
                    nextLevlNodeList.add(node.left);
                }
                if (node.right != null) {
                    nextLevlNodeList.add(node.right);
                }
            }
            result.add(sum / count);
            currentLevlNodeList = nextLevlNodeList;
        }
        return result;
    }

    public static void main(String[] args) {
        Program637 program637 = new Program637();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        System.out.println(program637.averageOfLevels(root));
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
