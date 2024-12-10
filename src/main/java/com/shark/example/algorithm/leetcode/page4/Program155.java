package com.shark.example.algorithm.leetcode.page4;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program155 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("min: " + minStack.getMin());
        minStack.pop();
        minStack.top();
        System.out.println("min: " + minStack.getMin());
    }

    static class MinStack {

        private final List<Integer> list;
        private Integer min = Integer.MAX_VALUE;

        public MinStack() {
            list = new ArrayList<>();
        }

        public void push(int val) {
            list.add(val);
            System.out.println("push: " + new Gson().toJson(list));
            if(val < min) {
                min = val;
            }
        }

        public void pop() {
            Integer pop = list.remove(list.size() - 1);
            System.out.println("pop: " + new Gson().toJson(list));
            if(Objects.equals(pop, min)) {
                min = Integer.MAX_VALUE;
                for(Integer value : list) {
                    if(value < min) {
                        min = value;
                    }
                }
            }
        }

        public int top() {
            System.out.println("top: " + new Gson().toJson(list));
            return list.get(list.size() - 1);
        }

        public int getMin() {
            System.out.println("getMin: " + new Gson().toJson(list));
            return min;
        }
    }
}
