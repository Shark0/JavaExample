package com.shark.example.algorithm.leetcode.page4;

import java.util.ArrayList;
import java.util.List;

public class Program173 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator binaryTree = new BSTIterator(root);
        System.out.println(binaryTree.next());
        System.out.println(binaryTree.next());
        System.out.println(binaryTree.hasNext());
        System.out.println(binaryTree.next());
        System.out.println(binaryTree.hasNext());
        System.out.println(binaryTree.next());
        System.out.println(binaryTree.hasNext());
        System.out.println(binaryTree.next());
        System.out.println(binaryTree.hasNext());
    }

    static class BSTIterator {

        int index = 0;
        List<TreeNode> list = new ArrayList<>();

        public BSTIterator(TreeNode root) {
            dfs(root, list);
        }

        public void dfs(TreeNode node, List<TreeNode> list) {
            if(node == null) {
                return;
            }
            dfs(node.left, list);
            list.add(node);
            dfs(node.right, list);
        }

        public int next() {
            TreeNode node = list.get(index);
            index ++;
            return node.val;
        }

        public boolean hasNext() {
            return index < list.size();
        }
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
