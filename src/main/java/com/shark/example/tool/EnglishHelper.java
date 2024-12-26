package com.shark.example.tool;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnglishHelper {

    List<String> loadWordList() {
        List<String> words = new ArrayList<>();
        String fileName = "file/english/words.txt";
        File file = new File(fileName);

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fileInputStream)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public void randomSelect(List<String> words, int selectCount) {
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (index < selectCount) {
            int random = (int) (Math.random() * words.size());
            String word = words.get(random);
            words.remove(random);
            stringBuilder.append(word);
            stringBuilder.append(", https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/");
            stringBuilder.append(word);
            stringBuilder.append("\n");
            index++;
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        EnglishHelper englishHelper = new EnglishHelper();
        List<String> words = englishHelper.loadWordList();
        englishHelper.randomSelect(words, 10);
    }

}
