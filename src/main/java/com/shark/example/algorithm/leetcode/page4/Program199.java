package com.shark.example.algorithm.leetcode.page4;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if(root == null) {
            return resultList;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            resultList.add(nodeList.getLast().val);
            List<TreeNode> childList = new ArrayList<>();
            for(TreeNode treeNode: nodeList) {
                if(treeNode.left != null) {
                    childList.add(treeNode.left);
                }
                if(treeNode.right != null) {
                    childList.add(treeNode.right);
                }
            }
            nodeList = childList;
        }
        return resultList;
    }


    public List<Integer> rightSideViewV1(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        if(root == null) {
            return resultList;
        }
        List<Integer> leftResultList = null;
        if(root.left != null) {
            leftResultList = rightSideViewV1(root.left);
        }
        List<Integer> rightResultList = null;
        if(root.right != null) {
            rightResultList = rightSideViewV1(root.right);
        }
        int max = 0;
        if(rightResultList != null) {
            max = rightResultList.size();
        }
        if(leftResultList != null && leftResultList.size() > max) {
            max = leftResultList.size();
        }

        resultList.add(root.val);
        for(int i = 0; i < max; i++) {
            if(rightResultList != null && rightResultList.size() > i) {
                resultList.add(rightResultList.get(i));
            } else {
                resultList.add(leftResultList.get(i));
            }
        }
        return resultList;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);;
        root.right = new TreeNode(3);
        System.out.println("result: " + new Gson().toJson(new Program199().rightSideView(root)));
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
