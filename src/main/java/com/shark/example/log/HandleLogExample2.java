package com.shark.example.log;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashSet;

public class HandleLogExample2 {
    public static void main(String[] argv) {
        String filePath = "D:\\HW\\Card\\Log\\gameRecord-out.log";

        File file = new File(filePath);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains("SQL語句執行出錯")) {
                    String sql = line.split("SQL語句執行出錯: ")[1].split(" message: ER_RECORD_FILE_FULL")[0];
                    System.out.println(sql);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
