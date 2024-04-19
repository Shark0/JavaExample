package com.shark.example.data.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringToDateExample {

    public static void main(String[] argv) {
        String dateString = "2020-12-31";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, dateTimeFormatter);
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("UTC+8"));
        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println(date.toInstant().atZone(ZoneId.of("UTC+8")).format(dateTimeFormatter));
    }
}
