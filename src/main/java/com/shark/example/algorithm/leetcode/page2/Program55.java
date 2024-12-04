package com.shark.example.algorithm.leetcode.page2;

public class Program55 {

    public boolean canJump(int[] nums) {
        int covrage = 0;
        int lastjump = 0;
        if (nums.length == 1) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            covrage = Math.max(covrage, i + nums[i]);
            if (i == lastjump) {
                lastjump = covrage;
                if (covrage >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canJump1(int[] nums) {
        int startIndex = 0;
        int[] jumps = new int[nums.length];
        jumps[0] = nums[0];
        int[] backs = new int[nums.length];
        while (true) {
            int jump = jumps[startIndex];
            if (jump == 0) {
                if (startIndex == 0) {
                    return nums.length == 1;
                }
                //back
                int back = backs[startIndex];
                backs[startIndex] = 0;
                startIndex = back;
//                System.out.println("back startIndex: " + startIndex);
            } else {
                int nextStartIndex = startIndex + jump;
                if (nextStartIndex >= nums.length - 1) {
                    return true;
                }
                //jump
                int nextJumpLimit = nums[nextStartIndex];
                jumps[nextStartIndex] = nextJumpLimit;
                jumps[startIndex] = jump - 1;
                backs[nextStartIndex] = startIndex;
                startIndex = nextStartIndex;
//                System.out.println("jump jumps: " + new Gson().toJson(jumps) + ", startIndex:" + startIndex +  ", nextJumpLimit:" + nextJumpLimit);
            }
        }
    }

    public static void main(String[] args) {
        System.out.printf("result: " + new Program55().canJump(new int[]{1, 1, 2, 2, 0, 1, 1}));
    }
}
