package com.shark.example.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateExample {

    public static void main(String argv[]) {
        String testData1 = "2019-01-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println("simple data format time: " + simpleDateFormat.parse(testData1).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long timeInMiles = 1561939200000L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeInMiles);
        Instant instant = date.toInstant();
        System.out.println("DateTimeFormatter parse result: " + instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter));
        System.out.println("SimpleDateFormat parse result: " + simpleDateFormat.format(date));
    }
}
