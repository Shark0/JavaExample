package com.shark.example.tool.english;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EnglishSortWordHelper {

    public void sort() {
        List<String> fileNameLst = List.of("words", "adv", "error");
        for(String fileName : fileNameLst) {
            String path = "file/english/" + fileName + ".txt";
            File file = new File(path);
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(fileInputStream)
            ) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                Set<String> wordSet = new HashSet<>();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        wordSet.add(line.trim().toLowerCase());
                    }
                }
                List<String> wordList = new ArrayList<>(wordSet);
                Collections.sort(wordList);
                StringBuilder stringBuilder = new StringBuilder();
                for (String word : wordList) {
                    stringBuilder.append(word);
                    stringBuilder.append("\n");
                }
                Files.write(Paths.get(path), stringBuilder.toString().getBytes());
                System.out.println("file name: " + fileName +  ", word count: " + wordSet.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new EnglishSortWordHelper().sort();
    }
}
