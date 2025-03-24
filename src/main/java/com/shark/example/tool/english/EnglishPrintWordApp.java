package com.shark.example.tool.english;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnglishPrintWordApp {

    public void printWordsInFiles(int selectCount) {

        List<String> words = new ArrayList<>();
        File file = new File("file/english/word.txt");
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
            int index = 0;
            while (index < selectCount && !words.isEmpty()) {
                int random = (int) (Math.random() * words.size());
                String word = words.get(random);
                stringBuilder.append(word);
                stringBuilder.append("\n");
                stringBuilder.append("https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/").append(word);
                stringBuilder.append("\n");
                stringBuilder.append("https://www.google.com/search?q=").append(word).append("&udm=2");
                stringBuilder.append("\n");
                index++;
            }
            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EnglishPrintWordApp englishHelper = new EnglishPrintWordApp();
        englishHelper.printWordsInFiles(10);
    }
}
