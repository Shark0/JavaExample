package com.shark.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shark.example.jackson.data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonExample1 {
    public static void main(String argv[]) {
        String json = null;

//        String json = "{\"importantColumnList\":[{\"columnId\":62751,\"weight\":0.006622423790402481},{\"columnId\":62752,\"weight\":0.010210079853008102},{\"columnId\":62757,\"weight\":0.9687535840620746},{\"columnId\":63105,\"weight\":0.014413912294514884}]} cannot be transformed to Json object; nested exception is java.lang.IllegalArgumentException: The given string value: {\"importantColumnList\":[{\"columnId\":62751,\"weight\":0.006622423790402481},{\"columnId\":62752,\"weight\":0.010210079853008102},{\"columnId\":62757,\"weight\":0.9687535840620746},{\"columnId\":63105,\"weight\":0.014413912294514884}]}";
//        ImportanceComponentInput input = new Gson().fromJson(json, ImportanceComponentInput.class);
        ImportanceComponentInput input = new ImportanceComponentInput();
        List<ImportanceColumnDio> importanceColumnDioList = new ArrayList<>();
        ImportanceColumnDio columnDio1 = ImportanceColumnDio.builder().columnId(1L).weight(0.5).build();
        importanceColumnDioList.add(columnDio1);
        ImportanceColumnDio columnDio2 = ImportanceColumnDio.builder().columnId(2L).weight(0.5).build();
        importanceColumnDioList.add(columnDio2);
        input.setImportantColumnList(importanceColumnDioList);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("json: " + json);

        try {
            System.out.println("json: " +  new Gson().toJson(objectMapper.readValue(json, ImportanceComponentInput.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
