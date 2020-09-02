package com.shark.example.parser.date;

import java.util.HashSet;

public class ParserDateExample {
    public static void main(String argv[]) {
        DateIdentificater dateIdentificater = new DateIdentificater();
        DateIdentificater.Result result = dateIdentificater.identify("2015年", new HashSet<>());
        System.out.println(result.getDatePattern());
    }
}
