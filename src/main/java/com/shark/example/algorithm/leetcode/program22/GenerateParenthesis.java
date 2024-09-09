package com.shark.example.algorithm.leetcode.program22;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        gp(n, "", 0, 0, list);
        return list;
    }

    public void gp(int n, String parenthesis, int open, int close, List<String> list) {
        if (close == n) {
            list.add(parenthesis);
            return;
        }
        if (open < n) {
            gp(n, parenthesis + "(", open + 1, close, list);
        }
        if (close < open) {
            gp(n, parenthesis + ")", open, close + 1, list);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();

        System.out.println(new Gson().toJson(generateParenthesis.generateParenthesis(3)));
    }
}
