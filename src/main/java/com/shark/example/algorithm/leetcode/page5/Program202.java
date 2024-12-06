package com.shark.example.algorithm.leetcode.page5;

import java.util.HashSet;
import java.util.Set;

public class Program202 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int temp = 0;
            while (n > 9) {
                int value = n % 10;
                n = n / 10;
                temp = (int) (temp + Math.pow(value, 2));
            }
            temp = (int) (temp + Math.pow(n, 2));
            if(temp == 1) {
                return true;
            }
            n = temp;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Program202().isHappy(7));
    }
}
