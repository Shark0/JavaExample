package com.shark.example.data.stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        int [] intArray = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8 ,9};
        Stack<Integer> stack = new Stack<>();
        for(Integer value : intArray) {
            stack.push(value);
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
