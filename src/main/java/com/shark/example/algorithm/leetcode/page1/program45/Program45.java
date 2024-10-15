package com.shark.example.algorithm.leetcode.page1.program45;

public class Program45 {

    public int jump(int[] nums) {

        int leftStep = nums.length - 1;
        int stepCount = 0;
        int currentIndex = 0;
        while (leftStep > 0) {
            int currentStepSize = nums[currentIndex];
            if(currentStepSize >= leftStep) {
                stepCount++;
                break;
            }
            System.out.println("currentStepSize: " + currentStepSize);
            //找最遠的
            int nextIndex = -1;
            int nextMaxStep = -1;
            for (int i = 0; i < currentStepSize; i++) {
                if ((currentIndex + i + 1) < nums.length) {
                    int tempStep = i + nums[currentIndex + i + 1];
                    if (tempStep > nextMaxStep) {
                        nextMaxStep = tempStep;
                        nextIndex = currentIndex + i + 1;
                    }
                }
            }

            stepCount++;
            leftStep = leftStep - (nextIndex + -currentIndex);
            System.out.println("nextIndex: " + nextIndex + ", nextMaxStep: " + nextMaxStep + ", leftStep = " + leftStep);
            currentIndex = nextIndex;
        }
        return stepCount;
    }

    public static void main(String[] args) {
        Program45 program45 = new Program45();
        int[] nums = new int[]{3,2,1};
        int result = program45.jump(nums);
        System.out.println("result = " + result);
    }
}
