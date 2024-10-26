package com.shark.example.algorithm.leetcode.page24;

import java.util.ArrayList;
import java.util.List;

public class Program1161 {

    public int maxLevelSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int maxDepth = 0;
        List<TreeNode> currentNodelist = List.of(root);
        int currentDepth = 1;
        while(!currentNodelist.isEmpty()) {

            List<TreeNode> childNodeList = new ArrayList<>();
            int sum = 0;
            for(TreeNode node : currentNodelist) {
                sum = sum + node.val;
                if(node.left != null) {
                    childNodeList.add(node.left);
                }
                if(node.right != null) {
                    childNodeList.add(node.right);
                }
            }
            System.out.println("sum = " + sum + ", currentDepth = " + currentDepth);
            if(sum > max) {
                max = sum;
                maxDepth = currentDepth;
            }
            currentNodelist = childNodeList;
            currentDepth ++;
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        root.right = new TreeNode(0);

        System.out.println("result: " + new Program1161().maxLevelSum(root));
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
