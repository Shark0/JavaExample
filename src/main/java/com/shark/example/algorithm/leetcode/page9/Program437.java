package com.shark.example.algorithm.leetcode.page9;

import com.google.gson.Gson;

import java.util.*;

public class Program437 {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> gapMap = new HashMap<>();
        gapMap.put((long) targetSum, 1);
        return dfs(root, gapMap, targetSum);
    }

    private int dfs(TreeNode root, Map<Long, Integer> previewGapMap, int targetSum) {
        if (root == null) {
            return 0;
        }

        int count = previewGapMap.getOrDefault((long) root.val, 0);
        System.out.println("value: " + root.val + ", count: " + count +
                ", previewGapMap: " + new Gson().toJson(previewGapMap) + ", targetSum: " + targetSum);

        Map<Long, Integer> gapMap = new HashMap<>();

        for(long previewKey : previewGapMap.keySet()) {
            long currentKey = previewKey - root.val;
            gapMap.put(currentKey, previewGapMap.getOrDefault(previewKey, 0));
        }
        gapMap.put((long) targetSum, gapMap.getOrDefault((long) targetSum, 0) + 1);
        System.out.println("gapMap: " + new Gson().toJson(gapMap));
        count = count + dfs(root.left, gapMap, targetSum) + dfs(root.right, gapMap, targetSum);
        return count;
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1000000000);
//        root.left = new TreeNode(1000000000);
//        root.left.left = new TreeNode(294967296);
//        root.left.left.left = new TreeNode(1000000000);
//        root.left.left.left.left = new TreeNode(1000000000);

//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//        root.left.right = new TreeNode(2);
//        root.left.right.right = new TreeNode(1);
//        root.right = new TreeNode(-3);
//        root.right.right = new TreeNode(11);

//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(4);
//        root.left.left = new TreeNode(11);
//        root.left.left.left = new TreeNode(7);
//        root.left.left.right = new TreeNode(2);
//        root.right = new TreeNode(8);
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4);
//        root.right.right.left = new TreeNode(5);
//        root.right.right.right = new TreeNode(1);

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);;
        root.right.right.right.right = new TreeNode(5);

        System.out.println("result: " + new Program437().pathSum(root, 3));
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
