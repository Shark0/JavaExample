package com.shark.example.date;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormatExample {

    public static void main(String[] argv) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH點");
        System.out.println(new Date().toInstant().atZone(ZoneId.of("UTC+8")).format(dateTimeFormatter));
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
        System.out.println(new Date().toInstant().atZone(ZoneId.of("UTC+16")).format(dateTimeFormatter));
        System.out.println(new Date().toInstant().atZone(ZoneId.of("UTC-8")).format(dateTimeFormatter));
    }
}
