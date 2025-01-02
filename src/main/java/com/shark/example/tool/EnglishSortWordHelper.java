package com.shark.example.tool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EnglishSortWordHelper {

    public void sort() {
        List<String> fileNameList = List.of(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z");

        for (String fileName : fileNameList) {
            String path = "file/english/" + fileName + ".txt";
            File file = new File(path);
            List<String> wordList = new ArrayList<>();
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader reader = new InputStreamReader(fileInputStream)) {
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        wordList.add(line);
                    }
                }

                Collections.sort(wordList);
                StringBuilder stringBuilder = new StringBuilder();
                for (String word : wordList) {
                    stringBuilder.append(word);
                    stringBuilder.append("\n");
                }
                Files.write(Paths.get(path), stringBuilder.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EnglishSortWordHelper().sort();
    }
}
