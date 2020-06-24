package com.shark.example.time;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeFormatterExample {
    public static void main(String argv[]) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年q季");
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter));
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy 'year' q'Q'");
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter));
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月");
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter));
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter));
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateTimeFormatter));

    }
}
