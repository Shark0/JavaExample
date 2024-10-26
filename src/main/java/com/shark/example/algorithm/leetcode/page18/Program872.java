package com.shark.example.algorithm.leetcode.page18;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program872 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = toList(root1);
        List<Integer> list2 = toList(root2);
        if (list1.size() != list2.size()) {
            return false;
        }
        System.out.println("root1: " + new Gson().toJson(root1));
        System.out.println("root2: " + new Gson().toJson(root2));
        System.out.println("list1: " + new Gson().toJson(list1));
        System.out.println("list2: " + new Gson().toJson(list2));

        for (int i = 0; i < list1.size(); i++) {
            int value1 = list1.get(i);
            int value2 = list2.get(i);
            if (value1 != value2) {
                return false;
            }
        }
        return true;
    }

    List<Integer> toList(TreeNode node) {
        if(node.left != null && node.right != null) {
            List<Integer> list = toList(node.left);
            list.addAll(toList(node.right));
            return list;
        } else if(node.left != null) {
            return toList(node.left);
        }  else if(node.right != null) {
            return toList(node.right);
        }
        List<Integer> list = new ArrayList<>();
        list.add(node.val);
        return list;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(7);
        root2.right = new TreeNode(1);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(2);
        root2.right.right.left = new TreeNode(9);
        root2.right.right.right = new TreeNode(8);
        System.out.println("result: " + new Program872().leafSimilar(root1, root2));
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
