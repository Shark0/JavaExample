package com.shark.example.algorithm.leetcode.page7;

public class Program334 {

    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int n : nums) {
            if(n <= min1) {
                min1 = n;
            } else if(n <= min2) {
                min2 = n;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean increasingTriplet1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int value1 = -1;
        int value2 = -1;
        int value3 = -1;


        for (int i = nums.length - 1; i >= 0; i--) {
            int value = nums[i];
            System.out.println("i: " + i + ", value: " + value);
            if (value1 == -1) {
                value1 = value;
                continue;
            } else {
                if (value >= value1) {
                    continue;
                }
                if (value2 == -1) {
                    int temp = value1;
                    value1 = value;
                    value = temp;
                } else {
                    if (value < value2) {
                        int temp = value1;
                        value1 = value;
                        value = temp;
                    }
                }
            }
            System.out.println("value: " + value);
            if (value > value1) {
                if (value2 == -1) {
                    value2 = value;
                    continue;
                } else {
                    int temp = value2;
                    value2 = value;
                    value = temp;
                }
            } else {
                continue;
            }

            if (value > value2) {
                value3 = value;
            }

            System.out.println("value1: " + value1 + ", value2: " + value2 + ", value3: " + value3);
            if (value3 > value2) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Program334 program334 = new Program334();
//        int[] input = new int[]{1, 2, 3, 4, 5};
//        int[] input = new int[]{5, 4, 3, 2, 1};
//        int[] input = new int[]{2, 1, 5, 0, 4, 6};
//        int[] input = new int[]{20, 100, 10, 12, 5, 13};
        int[] input = new int[]{1, 5, 0, 4, 1, 3};
        boolean result = program334.increasingTriplet(input);
        System.out.println("result: " + result);
    }
}
