package com.shark.example.file.txt;

import java.io.*;

public class PrintTxtExample {
    public static void main(String[] argv) {
        File file = new File("/Users/shiyongzhe/Documents/Shark/java/JavaExample/file/HighRiskAccountList.txt");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
