package com.shark.example.parser.date;

import java.util.HashSet;

public class ParserDateExample {
    public static void main(String argv[]) {
        DateIdentificater identificater = new DateIdentificater();
        DateIdentificater.Result result = identificater.identify("2015-01-01 00:07:51.155847680", new HashSet<>());
        System.out.println(result.getDatePattern());
    }
}
