package com.shark.example.file.txt;

import java.io.*;

public class ReadTxtFileExample {
    public static void main(String[] argv) {
        String filePath = "file/xml_example2.xml";
        ReadTxtFileExample readTxtFileExample = new ReadTxtFileExample();
        String txt = readTxtFileExample.readFile(filePath);
        System.out.println(txt);
    }

    public String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
