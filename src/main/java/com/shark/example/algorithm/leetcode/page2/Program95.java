package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program95 {

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

    public List<TreeNode> generateTrees(int n) {
        return generateSubTree(1, n);
    }

    private List<TreeNode> generateSubTree(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if(start > end) {
            return list;
        } else if(start == end) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = start;
            list.add(treeNode);
            return list;
        } else {
            for(int current = start; current <= end; current ++) {
                List<TreeNode> lefts = generateSubTree(start, current - 1);
                List<TreeNode> rights = generateSubTree(current + 1, end);
                if(lefts.isEmpty() && rights.isEmpty()) {
                    TreeNode currentTreeNode = new TreeNode();
                    currentTreeNode.val = current;
                    list.add(currentTreeNode);
                } else if(lefts.isEmpty()) {
                    for(TreeNode right: rights) {
                        TreeNode currentTreeNode = new TreeNode();
                        currentTreeNode.val = current;
                        currentTreeNode.right = right;
                        list.add(currentTreeNode);
                    }
                } else if(rights.isEmpty()) {
                    for(TreeNode left: lefts) {
                        TreeNode currentTreeNode = new TreeNode();
                        currentTreeNode.val = current;
                        currentTreeNode.left = left;
                        list.add(currentTreeNode);
                    }
                } else {
                    for(TreeNode left: lefts) {
                        for(TreeNode right: rights) {
                            TreeNode currentTreeNode = new TreeNode();
                            currentTreeNode.val = current;
                            currentTreeNode.left = left;
                            currentTreeNode.right = right;
                            list.add(currentTreeNode);
                        }
                    }
                }
            }
        }

        return list;
    }


    public static void main(String[] argv) {
        Program95 program95 = new Program95();
        System.out.println(new Gson().toJson(program95.generateTrees(3)));
    }
}
