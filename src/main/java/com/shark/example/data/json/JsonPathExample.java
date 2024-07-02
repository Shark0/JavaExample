package com.shark.example.data.json;

import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;

public class JsonPathExample {
    public static void main(String[] args) {
        String key = "district";
        String jsonString = "{\"delivery_codes\": [{\"postal_code\": {\"district\": \"Ghaziabad\", \"pin\": 201001, \"pre_paid\": \"Y\", \"cash\": \"Y\", \"pickup\": \"Y\", \"repl\": \"N\", \"cod\": \"Y\", \"is_oda\": \"N\", \"sort_code\": \"GB\", \"state_code\": \"UP\"}}]}";
        String jsonExpression = "$.delivery_codes[0].postal_code";
        HashMap<String, Object> map = JsonPath.parse(jsonString).read(jsonExpression);
        System.out.println(map.get(key));
    }
}
