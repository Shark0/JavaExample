package com.shark.example.algorithm.leetcode.page50;

import java.util.PriorityQueue;

public class Program2462 {

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> headPriorityQueue = new PriorityQueue<>();
        PriorityQueue<Integer> tailPriorityQueue = new PriorityQueue<>();
        int length = costs.length;
        int headIndex = 0;
        int tailIndex = 0;
        for (int i = 0; i < candidates; i++) {
            if(i <= (length - i -1)) {
                headPriorityQueue.add(costs[i]);
                headIndex = i;
            }
            if((length - i -1) > i) {
                tailPriorityQueue.add(costs[length - i -1]);
                tailIndex = length - i -1;
            }
        }
        System.out.println(headPriorityQueue);
        System.out.println(tailPriorityQueue);

        long cost = 0;
        for(int i = 0; i < k; i++) {
            int headValue = headPriorityQueue.isEmpty()? Integer.MAX_VALUE: headPriorityQueue.peek();
            int tailValue = tailPriorityQueue.isEmpty()? Integer.MAX_VALUE: tailPriorityQueue.peek();
            if(headValue <= tailValue) {
                System.out.println("p1Value: " + headValue);
                headPriorityQueue.poll();
                cost += headValue;
                if(headIndex + 1 < tailIndex) {
                    headIndex ++;
                    headPriorityQueue.add(costs[headIndex]);
                }
            } else {
                System.out.println("p2Value: " + tailValue);
                tailPriorityQueue.poll();
                cost += tailValue;
                if(tailIndex - 1 > headIndex) {
                    tailIndex --;
                    tailPriorityQueue.add(costs[tailIndex]);
                }
            }
            System.out.println("cost: " + cost);
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] costs = new int[]{31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        int k = 11;
        System.out.println("result: " + new Program2462().totalCost(costs, k, 2));
    }
}
