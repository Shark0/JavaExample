package com.shark.example.algorithm.leetcode.page6;

import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.PriorityQueue;

public class Program295 {

    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    static class MedianFinder {

        private final PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> large = new PriorityQueue<>();
        private boolean even = true;


        public MedianFinder() {

        }

        public void addNum(int num) {
            if (even) {
                large.offer(num);
                small.offer(large.poll());
            } else {
                small.offer(num);
                large.offer(small.poll());
            }
            System.out.println("num = " + num);
            System.out.println("small = " + new Gson().toJson(small));
            System.out.println("large = " + new Gson().toJson(large));
            even = !even;
        }

        public double findMedian() {
            if (even) {
                return (small.peek() + large.peek()) / 2.0;
            } else {
                return small.peek();
            }
        }
    }

}
