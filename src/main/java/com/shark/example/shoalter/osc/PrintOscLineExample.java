package com.shark.example.shoalter.osc;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PrintOscLineExample {
    public static void main(String[] argv) {
        String filePath = "mmsFile/osc/TO_BUILD01_FOR_20230711.txt";
        printOscLine(filePath);
    }

    private static void printOscLine(String filePath) {
        Gson gson = new Gson();
        try(FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            while (line != null) {
                String json = gson.toJson(line);
                System.out.println(json);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}