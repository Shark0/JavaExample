package com.shark.example.algorithm.leetcode.page2;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Program68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> results = new ArrayList<>();
        List<String> resultWords = new ArrayList<>();
        int resultWordsLength = 0;
        for (String word : words) {
            if (resultWordsLength + word.length() > maxWidth) {
                String resultRow = generateResultRow(resultWords, maxWidth);
                results.add(resultRow);
                resultWords.clear();
                resultWordsLength = 0;
            }
            resultWords.add(word);
            resultWordsLength = resultWordsLength + word.length() + 1;
        }
        results.add(generateLastResultRow(resultWords, maxWidth));
        return results;
    }

    public String generateResultRow(List<String> row, int maxWidth) {
        StringBuilder resultStringBuilder = new StringBuilder();
        int wordsLength = 0;
        for (String word : row) {
            wordsLength += word.length();
        }

        int spaceLength = (maxWidth - wordsLength) / (((row.size() == 1) ? 1 : (row.size()) - 1));
        int leftSpace = maxWidth - wordsLength - (spaceLength) * (row.size() - 1);
//        System.out.println(new Gson().toJson(row));
//        System.out.println("spaceLength: " + spaceLength + ", leftSpace: " + leftSpace);
        if (row.size() == 1) {
            resultStringBuilder.append(row.get(0));
            resultStringBuilder.append(" ".repeat(leftSpace));
        } else {
            int index = 0;
            for (String word : row) {
                resultStringBuilder.append(word);
                if (leftSpace > 0) {
                    resultStringBuilder.append(" ");
                    leftSpace--;
                }
                if (index < row.size() - 1) {
                    resultStringBuilder.append(" ".repeat(spaceLength));
                }
                index++;
            }
        }
        return resultStringBuilder.toString();
    }

    public String generateLastResultRow(List<String> row, int maxWidth) {
        StringBuilder resultStringBuilder = new StringBuilder();
        int index = 0;
        for (String word : row) {
            resultStringBuilder.append(word);
            if (index != row.size() - 1) {
                resultStringBuilder.append(" ");
            }
            index++;
        }
        int leftSpace = maxWidth - resultStringBuilder.length();
        if (leftSpace > 0) {
            resultStringBuilder.append(" ".repeat(leftSpace));
        }
        return resultStringBuilder.toString();
    }

    public static void main(String[] args) {
        String[] words = new String[]{"AAA"};
        List<String> result = new Program68().fullJustify(words, 20);
        System.out.println(new Gson().toJson(result));
    }
}
