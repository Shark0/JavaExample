package com.shark.example.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.shark.example.jackson.data.ComparisonOperatorType;
import com.shark.example.jackson.data.LogicOperatorType;
import com.shark.example.jackson.data.SqlConditionInput;
import com.shark.example.jackson.data.SqlConditionType;

import java.io.IOException;
import java.util.List;

public class JacksonExample {
    public static void main(String argv[]) {
        SqlConditionInput input = new SqlConditionInput();
        input.setType(SqlConditionType.NORMAL);
        input.setValueList(List.of("1", "2"));
        input.setColumnName("c1");
        input.setComparisonOperatorType(ComparisonOperatorType.IN);

        SqlConditionInput next = new SqlConditionInput();
        next.setType(SqlConditionType.NORMAL);
        next.setValueList(List.of("1", "2"));
        next.setColumnName("c1");
        next.setComparisonOperatorType(ComparisonOperatorType.IN);

        input.setNext(LogicOperatorType.OR, next);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("json: " + json);

        try {
            System.out.println("json: " +  new Gson().toJson(objectMapper.readValue(json, SqlConditionInput.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
