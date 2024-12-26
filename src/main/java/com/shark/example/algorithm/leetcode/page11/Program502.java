package com.shark.example.algorithm.leetcode.page11;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Program502 {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int length = profits.length;
        int[][] projects = new int[length][2];
        for (int i = 0; i < length; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
        System.out.println("projects: " + new Gson().toJson(projects));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        int index = 0;
        while (k > 0) {
            while (index < length && projects[index][0] <= w) {
                System.out.println("index: " + index + ", w: " + w + ", capital: " + projects[index][0]);
                priorityQueue.offer(projects[index][1]);
                index++;
            }
            System.out.println("k: "+ k + ", priorityQueue: " + new Gson().toJson(priorityQueue));
            if (priorityQueue.isEmpty()) {
                break;
            }
            w = w + priorityQueue.poll();
            k--;
        }
        return w;
    }


    public static void main(String[] args) {
        int[] profits = new int[]{1, 2, 3};
        int[] capital = new int[]{0, 1, 1};
        System.out.println(new Program502().findMaximizedCapital(2, 0, profits, capital));
    }
}
