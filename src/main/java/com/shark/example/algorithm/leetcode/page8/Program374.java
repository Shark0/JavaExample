package com.shark.example.algorithm.leetcode.page8;

public class Program374 {

    public int guess(int num) {
        int pickedNumber = 1702766719;
        return Integer.compare(pickedNumber, num);
    }

    public int guessNumber(int n) {
        long result = 0;

        long start= 0;
        long end = n;

        while (start <= end) {
            result = (start + end) / 2;
            System.out.println("guess result:" + result);
            int guess = guess((int) result);
            if (guess == 0) {
                return (int) result;
            } else if (guess == 1) {
                start = result + 1;
            } else {
                end = result - 1;
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println("result: " + new Program374().guessNumber(1702766719));
    }
}
