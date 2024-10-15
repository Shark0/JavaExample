package com.shark.example.algorithm.leetcode.page1.program17;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> currentResultList = new ArrayList<>();

        for(int i = 0; i < digits.length(); i++) {
            List<String> numberStringList = getStrings(digits, i);
            if(i == 0) {
                currentResultList = numberStringList;
            } else {
                List<String> resultList = new ArrayList<>();
                for(String numberString : numberStringList) {
                    for(String currentNumberString: currentResultList) {
                        resultList.add(currentNumberString + numberString);
                    }
                }
                currentResultList = resultList;
            }
        }
        return currentResultList;
    }

    private List<String> getStrings(String digits, int i) {
        char c = digits.charAt(i);
        return switch (c) {
            case '2' -> List.of("a", "b", "c");
            case '3' -> List.of("d", "e", "f");
            case '4' -> List.of("g", "h", "i");
            case '5' -> List.of("j", "k", "l");
            case '6' -> List.of("m", "n", "o");
            case '7' -> List.of("p", "q", "r", "s");
            case '8' -> List.of("t", "u", "v");
            case '9' -> List.of("w", "x", "y", "z");
            default -> List.of();
        };
    }

    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        System.out.println(letterCombinations.letterCombinations("23"));
    }

}
