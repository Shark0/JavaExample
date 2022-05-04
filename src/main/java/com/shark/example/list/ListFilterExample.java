package com.shark.example.list;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ListFilterExample {

    public static void main(String[] argv) {
        List<BigDecimal> list = List.of(new BigDecimal(0), new BigDecimal(1), new BigDecimal(2));

        BigDecimal minCondition = new BigDecimal(0);
        BigDecimal maxCondition = new BigDecimal(2);

        list = list.stream().filter(item ->
                item.compareTo(minCondition) > 0 && item.compareTo(maxCondition) < 0
        ).collect(Collectors.toList());

        System.out.println(new Gson().toJson(list));

    }
}
