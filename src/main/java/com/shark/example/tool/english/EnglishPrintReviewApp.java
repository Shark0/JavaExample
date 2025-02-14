package com.shark.example.tool.english;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnglishPrintReviewApp {

    public void printWordsInFiles() {
        List<String> words = new ArrayList<>();
        File file = new File("file/english/review.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String word : words) {
                stringBuilder.append(word);
                stringBuilder.append("\n");
                stringBuilder.append("https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/").append(word);
                stringBuilder.append("\n");
                stringBuilder.append("https://www.google.com/search?q=").append(word).append("&udm=2");
                stringBuilder.append("\n");
            }
            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EnglishPrintReviewApp englishHelper = new EnglishPrintReviewApp();
        englishHelper.printWordsInFiles();
    }
}
