package com.shark.example.algorithm.leetcode.page3;

import java.util.Stack;

public class Program150 {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        int value1;
        int value2;
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    stack.push(value2 + value1);
                    break;
                case "-":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    stack.push(value2 - value1);
                    break;
                case "*":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    stack.push(value2 * value1);
                    break;
                case "/":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    stack.push(value2 / value1);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    public static void main(String[] argv) {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(new Program150().evalRPN(tokens));
    }
}
