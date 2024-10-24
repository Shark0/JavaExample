package com.shark.example.algorithm.leetcode.page5;

import com.google.gson.Gson;

public class Program236 {

    private TreeNode result = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        //find p
        find(root, p, q);
        return result;
    }

    private int find(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return 0;
        }
        boolean isFindP = root.val == p.val;
        boolean isFindQ = root.val == q.val;
        if(isFindP) {
            isFindQ = hasOneNode(root.left, q) || hasOneNode(root.right, q);
            if(isFindQ) {
                result =  root;
                return 2;
            } else {
                return 1;
            }
        }
        if(isFindQ) {
            isFindP = hasOneNode(root.left, p) || hasOneNode(root.right, p);
            if(isFindP) {
                result =  root;
                return 2;
            } else {
                return 1;
            }
        }

        int findCountByLeft = find(root.left, p, q);
        if(findCountByLeft == 2) {
            return 2;
        }
        int findCountByRight = find(root.right, p, q);
        if(findCountByRight == 2) {
            return 2;
        }
        if(findCountByRight + findCountByLeft == 2) {
            result = root;
            return 2;
        }
        return findCountByLeft + findCountByRight;
    }

    public boolean hasOneNode(TreeNode node, TreeNode target) {
        if (node == null) {
            return false;
        }

        if (node.val == target.val) {
            return true;
        }

        if (node.left != null) {
            boolean hasOneNode = hasOneNode(node.left, target);
            if (hasOneNode) {
                return true;
            }
        }

        if (node.right != null) {
            return hasOneNode(node.right, target);
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(1);


        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(2);

        System.out.println("result: " + new Gson().toJson(new Program236().lowestCommonAncestor(root, p, q)));
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
