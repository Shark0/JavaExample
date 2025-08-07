package com.shark.example.algorithm.leetcode.page69;

import java.util.Arrays;

public class Program3440 {

    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int lastEndTime = 0;
        int[] freeTimes = new int[startTime.length + 1];
        for (int i = 0; i < startTime.length; i++) {
            freeTimes[i] = startTime[i] - lastEndTime;
            lastEndTime = endTime[i];
        }
        freeTimes[startTime.length] = eventTime - lastEndTime;
        System.out.println("freeTimes: " + Arrays.toString(freeTimes));
        int[] maxRightFreeTimes = new int[startTime.length + 1];
        for (int i = startTime.length - 1; i >= 0; i--) {
            maxRightFreeTimes[i] = Math.max(freeTimes[i + 1], maxRightFreeTimes[i + 1]);
        }
        System.out.println("maxRightFreeTimes: " + Arrays.toString(maxRightFreeTimes));

        int maxFreeTime = 0;
        int maxLeftFreeTime = 0;
        for (int i = 1; i <= startTime.length; i++) {
            int workTime = endTime[i - 1] - startTime[i - 1];
            if (maxLeftFreeTime >= workTime || maxRightFreeTimes[i] >= workTime) {
                maxFreeTime = Math.max(maxFreeTime, freeTimes[i - 1] + workTime + freeTimes[i]);
            }
            maxFreeTime = Math.max(maxFreeTime, freeTimes[i - 1] + freeTimes[i]);
            maxLeftFreeTime = Math.max(maxLeftFreeTime, freeTimes[i - 1]);
        }
        return maxFreeTime;
    }

    public static void main(String[] args) {
        Program3440 program3440 = new Program3440();
        int[] startTime = new int[]{0, 7, 9};
        int[] endTime = new int[]{1, 8, 10};
        int maxFreeTime = program3440.maxFreeTime(10, startTime, endTime);
        System.out.println("maxFreeTime: " + maxFreeTime);
    }
}
