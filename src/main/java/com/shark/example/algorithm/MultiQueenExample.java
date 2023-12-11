package com.shark.example.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MultiQueenExample {
    public static void main(String[] argv) {
        MultiQueenExample multiQueenExample = new MultiQueenExample();
        List<List<String>> results = multiQueenExample.solve(8);
        multiQueenExample.printResult(results);
    }

    public List<List<String>> solve(int checkerboardSize) {
        List<List<String>> results = new ArrayList<>();
        char[][] result = new char[checkerboardSize][checkerboardSize];
        //init checkerboard
        for (int x = 0; x < checkerboardSize; x++) {
            for (int y = 0; y < checkerboardSize; y++) {
                result[x][y] = '.';
            }
        }
        backtrack(results, result, checkerboardSize, 0);
        return results;
    }

    private void backtrack(List<List<String>> results, char[][] result, int checkerboardSize, int x) {
        for (int y = 0; y < checkerboardSize; y++) {
            if (isValid(result, x, y)) {
                result[x][y] = 'Q';
                if (x == checkerboardSize - 1) {
                    showResult(results, result);
                } else {
                    backtrack(results, result, checkerboardSize, x + 1);
                }
                result[x][y] = '.';
            }
        }
    }

    private boolean isValid(char[][] result, int x, int y) {
        //上
        for (int i = 0; i < x; ++i) {
            if (result[i][y] == 'Q') {
                return false;
            }
        }
        //左上
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (result[i][j] == 'Q') {
                return false;
            }
        }
        //又上
        for (int i = x - 1, j = y + 1; i >= 0 && j < result.length; i--, j++) {
            if (result[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private void showResult(List<List<String>> results, char[][] result) {
        List<String> list = new ArrayList<>(result.length);
        for (char[] value : result) {
            list.add(new String(value));
        }
        results.add(list);
    }

    public void printResult(List<List<String>> results) {
        for(int i = 0; i < results.size(); i++) {
            List<String> result = results.get(i);
            System.out.println("Result" + i);
            for(String row: result) {
                System.out.println(row);
            }
        }
    }
}
