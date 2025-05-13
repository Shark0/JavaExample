package com.shark.example.algorithm.leetcode.page3;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program131 {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            System.out.println("start: " + start + ", end: " + end + ", path: " + new Gson().toJson(path) + ", s: " + s.substring(start, end));
            if (isPalindrome(s, start, end - 1)) {
                path.add(s.substring(start, end));
                backtrack(s, end, path, result);
                path.removeLast();
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Program131 program131 = new Program131();
        List<List<String>> result = program131.partition("aba");
        System.out.println(new Gson().toJson(result));
    }

}
