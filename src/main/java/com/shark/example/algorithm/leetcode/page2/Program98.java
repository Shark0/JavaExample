package com.shark.example.algorithm.leetcode.page2;

public class Program98 {

    public boolean isValidBST(TreeNode root) {
        int[] result = checkNode(root);
        return result[0] == 1;
    }

    public int[] checkNode(TreeNode node) {
        int[] result = new int[3]; //0: check 1: min 2: max
        if(node.left != null) {
            int[] left = checkNode(node.left);
            int check = left[0];
            if(check == 0) {
                return result;
            }
            int max = left[2];
            if(max >= node.val) {
                return result;
            }
            result[1] = left[1];
        } else {
            result[1] = node.val;
        }

        if(node.right != null) {
            int[] right = checkNode(node.right);
            int check = right[0];
            if(check == 0) {
                return result;
            }
            int min = right[1];
            if(min <= node.val) {
                return result;
            }
            result[2] = right[2];
        } else {
            result[2] = node.val;
        }
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        System.out.println(new Program98().isValidBST(root));
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
