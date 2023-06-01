package com.shark.example.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shark.example.util.StringUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class FileExample3 {

    public static void main(String[] argv) {
        Set<String> tplUuidSet = generateTplUuidSet();
        HashMap<String, String> uuidSkuCodeHashMap = generateMmsProductHashMap();
        compareTwoResult(tplUuidSet, uuidSkuCodeHashMap);
    }

    private static Set<String> generateTplUuidSet() {
        StringBuilder jsonStringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader("file/api.log");
            BufferedReader reader = new BufferedReader(fileReader);) {

            String line = reader.readLine();
            while (line != null) {
                jsonStringBuilder.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = jsonStringBuilder.toString();
        Gson gson = new Gson();
        HashSet<String> tplUuidSet = new HashSet<>();
        Type type = new TypeToken<ArrayList<ExampleThreeDto>>() {
        }.getType();
        List<ExampleThreeDto> exampleThreeDtoList = gson.fromJson(json, type);
        for (ExampleThreeDto exampleThreeDto : exampleThreeDtoList) {
            tplUuidSet.add(exampleThreeDto.getUuid());
        }
        return tplUuidSet;
    }

    private static HashMap<String, String> generateMmsProductHashMap() {
        HashMap<String, String> uuidSkuCodeHashMap = new HashMap<>();
        try(FileReader fileReader = new FileReader("file/sql_result.log");
            BufferedReader reader = new BufferedReader(fileReader);) {

            String line = reader.readLine();
            while (line != null) {
                String[] rowResult = line.split("\t");
                String skuCode = rowResult[1].trim();
                String uuid = rowResult[2].trim();
                uuidSkuCodeHashMap.put(uuid, skuCode);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uuidSkuCodeHashMap;
    }

    private static void compareTwoResult(Set<String> tplUuidSet, HashMap<String, String> uuidSkuCodeHashMap) {
        System.out.println("tplUuidSetSize: " + tplUuidSet.size());
        System.out.println("uuidSkuCodeHashMap: " + uuidSkuCodeHashMap.size());
        Set<String> notInMmsSet = new HashSet<>();
        for(String uuid: tplUuidSet) {
            if(StringUtil.isEmpty(uuidSkuCodeHashMap.get(uuid))) {
                System.out.println("uuid not in mms: " + uuid);
                notInMmsSet.add(uuid);
            }
        }
        System.out.println("notInMmsSet: " + notInMmsSet.size());
    }
}
