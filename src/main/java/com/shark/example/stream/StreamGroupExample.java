package com.shark.example.stream;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamGroupExample {
    public static void main(String[] argv) {
        List<List<Object>> rowList = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            List<Object> row = new ArrayList<>();
            row.add(i % 2 == 0? "A" : "Z");
            row.add(Long.valueOf(i));
            rowList.add(row);
        }
        System.out.println("rowList: " + new Gson().toJson(rowList));

        Map<Object, List<List<Object>>> map = rowList.stream().collect(Collectors.groupingBy(list -> list.get(0)));
        System.out.println("rowList: " + new Gson().toJson(map));
    }
}
