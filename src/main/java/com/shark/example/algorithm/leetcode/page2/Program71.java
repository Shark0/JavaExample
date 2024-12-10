package com.shark.example.algorithm.leetcode.page2;

import java.util.Stack;

public class Program71 {

    public String simplifyPath(String path) {
        String result = "";
        String[] subPaths = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String subPath : subPaths){
            if(subPath.isEmpty()) {
                continue;
            }
            if(subPath.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            } else if(!subPath.equalsIgnoreCase(".")) {
                stack.push(subPath);
            }
        }
        while(!stack.isEmpty()){
            String subPath = stack.pop();
            result = subPath + result;
            result = "/" + result;
        }
        if(result.isEmpty()){
            result = "/";
        }

        return result;
    }

    public static void main(String[] args) {
        String path = "/home/";
        System.out.println(new Program71().simplifyPath(path));
    }
}
