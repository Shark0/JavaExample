package com.shark.example.algorithm.leetcode.page5;

import java.util.Stack;

public class Program224 {

    public int calculate(String s) {
        int result = 0;
        int num = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    num = num * 10 + (c - '0');
                    break;
                case '+':
                    result = result + sign * num;
                    sign = 1;
                    num = 0;
                    break;
                case '-':
                    result = result + sign * num;
                    sign = -1;
                    num = 0;
                    break;
                case '(':
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                    break;
                case ')':
                    result = result + sign * num;
                    int previewSign = stack.pop();
                    result = result * previewSign;
                    int previewResult = stack.pop();
                    result = result + previewResult;
                    num = 0;
                    break;
                default:
                    break;
            }
        }

        result += sign * num;
        return result;
    }

    public static void main(String[] argv) {
        System.out.println(new Program224().calculate("1+1"));
    }
}

