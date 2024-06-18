package com.shark.example.file.txt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveTxtExample {
    public static void main(String[] argv) {
        String content = "123456";
        String path = "file/txt_example.txt";
        try {
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
