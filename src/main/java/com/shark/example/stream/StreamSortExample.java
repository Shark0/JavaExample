package com.shark.example.stream;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortExample {

    public static void main(String[] argv) {
        List<List<Object>> rowList = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            List<Object> row = new ArrayList<>();
            row.add(i % 2 == 0? "A" : "Z");
            row.add(Long.valueOf(i % 5));
            row.add(Double.valueOf(100 - i));
            row.add(i < 5);
            row.add(new Date());
            rowList.add(row);
        }
        System.out.println(new Gson().toJson(rowList));

        Comparator<List<Object>> comparator = null;
        for(int i = 0; i < 5; i ++) {
            switch (i) {
                case 0:
                    if(comparator == null) {
                        comparator = Comparator.comparing(row -> ((String) row.get(0)));
                        comparator = comparator.reversed();
                    } else {
                        comparator = comparator.thenComparing(row -> ((String) row.get(0)));
                        comparator = comparator.reversed();
                    }
                    break;
                case 1:
                    if(comparator == null) {
                        comparator = Comparator.comparing(row -> ((Long) row.get(1)));
                        comparator = comparator.reversed();
                    } else {
                        comparator = comparator.thenComparing(row -> ((Long) row.get(1)));
                        comparator = comparator.reversed();
                    }
                    break;
                case 2:
                    if(comparator == null) {
                        comparator = Comparator.comparing(row -> ((Double) row.get(2)));

                    } else {
                        comparator = comparator.thenComparing(row -> ((Double) row.get(2)));
                    }
                    break;
                case 3:
                    if(comparator == null) {
                        comparator = Comparator.comparing(row -> ((Boolean) row.get(3)));
                    } else {
                        comparator = comparator.thenComparing(row -> ((Boolean) row.get(3)));
                    }
                    break;
                case 4:
                    if(comparator == null) {
                        comparator = Comparator.comparing(row -> ((Date) row.get(4)).getTime());
                    } else {
                        comparator = comparator.thenComparing(row -> ((Date) row.get(4)).getTime());
                    }
                    break;
            }
        }

//        rowList = rowList.stream().sorted(comparator).collect(Collectors.toList());
        rowList = rowList.stream().sorted(comparator).collect(Collectors.toList());
        System.out.println(new Gson().toJson(rowList));
    }

}
