package com.shark.example.shoalter.osc;

import com.google.gson.Gson;
import com.shark.example.data.string.StringUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class OscExample {
    public static void main(String[] argv) {
        String filePath = "mmsFile/osc/TO_BUILD01_FOR_20230711.txt";
        OscExample oscExample = new OscExample();
        oscExample.checkFile(filePath);
        String transformFilePath = "mmsFile/osc/TO_BUILD01_FOR_20230711_TRANSFORM.txt";
        oscExample.transformTableCase(true, filePath, transformFilePath);
    }

    public void checkFile(String filePath) {
        Set<String> tableSet = new HashSet<>();
        try(FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index= 0;
            while (line != null) {
                if(!StringUtil.isEmpty(line)) {
                    String[] commandArray = line.split("\t", -1);
                    if(commandArray.length < 3) {
                        throw new RuntimeException(" tab exception in line " + (index + 1));
                    }
                    String table = commandArray[1];
                    if(tableSet.contains(table)) {
                        throw new RuntimeException(" table repeat in line " + (index + 1));
                    }
                    tableSet.add(table);
                }
                line = reader.readLine();
                index ++;
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public void transformTableCase(boolean isUppercase, String filePath, String transferFilePath) {
        StringBuilder stringBuilder = new StringBuilder();

        try(FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader)) {
            String line = reader.readLine();
            int index= 0;
            while (line != null) {
                if(!StringUtil.isEmpty(line)) {
                    if(index != 0) {
                        stringBuilder.append("\n");
                    }
                    String[] commandArray = line.split("\t", -1);
                    if(commandArray.length < 3) {
                        throw new RuntimeException(" tab exception in line " + (index + 1));
                    }
                    String schema = commandArray[0];
                    String table = commandArray[1];
                    if(isUppercase) {
                        table = table.toUpperCase();
                    } else {
                        table = table.toLowerCase();
                    }
                    String ddl = commandArray[2];
                    stringBuilder.append(schema).append("\t").append(table).append("\t").append(ddl).append("\n");
                }
                line = reader.readLine();
                index ++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.write(Paths.get(transferFilePath), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}