package com.shark.example.log;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashSet;

public class HandleLogExample {
    public static void main(String[] argv) {
        String filePath = "D:\\HW\\Card\\Log\\gameRecord-out.log";

        HashSet<String> nameSet = new HashSet<>();
        File file = new File(filePath);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println("line = " + line);
                if(line.contains("name")) {
                    String name = line.replace("\\", "").split("\"name\":\"")[1].split("\",")[0];
                    nameSet.add(name);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("nameSet = " + new Gson().toJson(nameSet));
    }
}
