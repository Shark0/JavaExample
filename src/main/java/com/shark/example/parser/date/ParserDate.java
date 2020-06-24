package com.shark.example.parser.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

public class ParserDate {

    public static void main(String[] argv) {
//        String input = "2018-01-04";
//
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .appendPattern("yyyy-MM-dd hh:mm:ss.SSS")
//                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
//                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
//                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
//                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
//                .toFormatter();
//
//
//        LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
//        System.out.println(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()).getTime());
        DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String input = "2017-05-19 04:00:00.000";
        LocalDateTime localDateTime = LocalDateTime.parse(input, DATA_TIME_FORMATTER);
        Timestamp.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
