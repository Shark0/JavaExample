package com.shark.example.algorithm.leetcode.page47;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Program2336 {

    private final PriorityQueue<Integer> addBackPriorityQueue = new PriorityQueue<>();
    private int min = 1;

    public int popSmallest() {

        if(!addBackPriorityQueue.isEmpty()) {
            return addBackPriorityQueue.poll();
        }

        int value = min;
        min++;
        return value;
    }

    public void addBack(int num) {
        if (num >= min) {
            return;
        }
        if(!addBackPriorityQueue.contains(num)) {
            addBackPriorityQueue.add(num);
        }

    }

    public static void main(String[] args) {
        Program2336 program = new Program2336();
        program.addBack(2);
        System.out.println(program.popSmallest());
        System.out.println(program.popSmallest());
        program.addBack(1);
        System.out.println(program.popSmallest());
        System.out.println(program.popSmallest());
        System.out.println(program.popSmallest());
    }
}
