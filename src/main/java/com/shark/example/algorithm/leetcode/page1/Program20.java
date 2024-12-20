package com.shark.example.algorithm.leetcode.page1;

import java.util.Stack;

public class Program20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char preview = stack.pop();
                if(c == ')' && preview != '(') {
                    return false;
                }
                if(c == '}' && preview != '{') {
                    return false;
                }
                if(c == ']' && preview != '[') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        Program20 validParentheses = new Program20();
        System.out.println(validParentheses.isValid("()"));
    }
}
