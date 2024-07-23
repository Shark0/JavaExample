package com.shark.example.algorithm.leetcode.zigzagConversion;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        List<List<String>> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<String> row = new ArrayList<>();
            rows.add(row);
        }

        int charIndex = 0;
        int roundSize;
        if(numRows < 3) {
            roundSize = numRows;
        } else {
            roundSize = numRows * 2 -2;
        }
        while (charIndex < s.length()) {
            String value = s.substring(charIndex, charIndex + 1);
            int roundIndex = charIndex % roundSize;
            int rowIndex;
            if(roundIndex < numRows) {
                rowIndex = roundIndex;
            } else {
                rowIndex = (roundSize - roundIndex);
            }
            List<String> row = rows.get(rowIndex);
            row.add(value);
            charIndex ++;
        }
        StringBuilder sb = new StringBuilder();
        for(List<String> row : rows) {
            for (String value : row) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion zigzagConversion = new ZigzagConversion();
        System.out.println(zigzagConversion.convert("ABCDEFG", 2));
    }
}
