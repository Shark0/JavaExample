package com.shark.example.data.priorityqueue;

import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 1000; i >= 0; i--) {
            priorityQueue.add(i);
        }

        for (int i = 1000; i >= 0; i--) {
            System.out.println(priorityQueue.poll());
        }
    }
}
