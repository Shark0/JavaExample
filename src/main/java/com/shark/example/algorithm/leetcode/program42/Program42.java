package com.shark.example.algorithm.leetcode.program42;

public class Program42 {

    public int trap(int[] height) {
        int trap = 0;
        int rightSideIndex = -1;
        int rightSideHeight = 0;
        int leftSideIndex = -1;
        int leftSideHeight = 0;

        int tempRightIndex = 0;

        while (tempRightIndex < (height.length - 2)) {
            System.out.println("tempRightIndex: " + tempRightIndex);
            //find right side
            if (rightSideIndex == -1) {
                int currentHeight = height[tempRightIndex];
                int nextHeight = height[tempRightIndex + 1];
                if (currentHeight > nextHeight) {
                    rightSideIndex = tempRightIndex;
                    rightSideHeight = currentHeight;
                }

            }
            //find left side
            if (rightSideIndex != -1) {
                int tempLeftIndex = tempRightIndex + 2;
                while (tempLeftIndex < height.length) {
                    int currentHeight = height[tempLeftIndex];
                    int previewHeight = height[tempLeftIndex - 1];
                    if (currentHeight > previewHeight) {
                        if (currentHeight > leftSideHeight) {
                            leftSideIndex = tempLeftIndex;
                            leftSideHeight = currentHeight;
                        }
                    }
                    if (leftSideHeight >= rightSideHeight) {
                        break;
                    }
                    tempLeftIndex++;
                }
            }
            //calculate
            if (rightSideIndex != -1 && leftSideIndex != -1) {
                int minHeight = Math.min(rightSideHeight, leftSideHeight);
                System.out.println("rightSideIndex: " + rightSideIndex +
                        ", leftSideIndex: " + leftSideIndex +
                        ", minHeight: " + minHeight);
                for (int i = rightSideIndex + 1; i < leftSideIndex; i++) {
                    int currentHeight = height[i];
                    if (minHeight > currentHeight) {
                        int currentTrap = minHeight - currentHeight;
                        System.out.println("currentTrap: " + currentTrap + " i: " + i);
                        trap = currentTrap + trap;
                    }
                }
                tempRightIndex = leftSideIndex;
                rightSideIndex = -1;
                rightSideHeight = 0;
                leftSideIndex = -1;
                leftSideHeight = 0;
                continue;
            }
            tempRightIndex++;
        }
        return trap;
    }

    public static void main(String[] args) {
        Program42 program = new Program42();
        int result = program.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println("result = " + result);
    }
}
