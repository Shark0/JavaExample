package com.shark.example.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileExample2 {
    public static void main(String[] argv) {
        List<String> uuidList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("file/data.txt"));
            String line = reader.readLine();
            while (line != null) {
//                System.out.println(line);
                String[] array = line.trim().split(" ");
                String uuid = array[1].replace("\"", "'");
//                System.out.println(uuid);
                uuidList.add(uuid);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("use mms;").append("\n");
        stringBuilder.append("select UUID, PRODUCT_READY_METHOD from PRODUCT where UUID in (");
        for(int i = 0; i < uuidList.size(); i ++) {
            if(i != 0) {
                stringBuilder.append(",");
                stringBuilder.append("\n");
            }
            stringBuilder.append(uuidList.get(i));
        }
        stringBuilder.append(");");

        System.out.println(stringBuilder);

    }
}
