package com.shark.example.date;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormatExample2 {

    public static void main(String[] argv) {
        Date date = new Date();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH點");

        System.out.println(date.toInstant().atZone(ZoneId.of("UTC+0")).format(dateTimeFormatter));
        System.out.println(date.toInstant().atZone(ZoneId.of("UTC+8")).format(dateTimeFormatter));
    }
}
