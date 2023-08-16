package com.shark.example.collection;

import com.google.gson.Gson;
import com.shark.example.collection.pojo.SortDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSortExample {

    public static void main(String[] argv) {
        List<SortDto> list = new ArrayList<>();
        list.add(SortDto.builder().value(30).build());
        list.add(SortDto.builder().value(100).build());
        list.add(SortDto.builder().value(10).build());

        Collections.sort(list,  (pojo1, pojo2) -> {
            return (pojo1.getValue() - pojo2.getValue() > 0) ? -1 : 1;
        });

        System.out.println(new Gson().toJson(list));
    }
}
