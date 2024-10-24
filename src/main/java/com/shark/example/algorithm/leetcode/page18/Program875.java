package com.shark.example.algorithm.leetcode.page18;

public class Program875 {
    public int minEatingSpeed(int[] piles, int h) {
        int head = 1;
        int tail = 0;
        for (int pile : piles) {
            if (pile > tail) {
                tail = pile;
            }
        }

        while (head <= tail) {
            int medium = (head + tail) / 2;
            long eatHour = calculateEatHour(piles, medium);
            System.out.println("medium: " + medium + ", eatHour: " + eatHour);
            if (eatHour <= h) {
                tail = medium - 1;
            } else {
                head = medium + 1;
            }
        }
        return head;
    }

    public long calculateEatHour(int[] piles, int eatCount) {
        long hour = 0;
        for (int pile : piles) {
            hour = hour + (pile / eatCount);
            if (pile % eatCount != 0) {
                hour++;
            }
        }
        return hour;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{805306368, 805306368, 805306368};
        int hour = 1000000000;
        System.out.println("result: " + new Program875().minEatingSpeed(piles, hour));
    }
}
