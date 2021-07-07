package com.shark.example.parser.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateToLongExample {
    public static void main(String[] argv) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 3, 7, 14, 0, 0);
        Date startTime = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(startTime));
        System.out.println("startTime: " + startTime.getTime());
        calendar.set(2021, 3, 7, 14, 30, 0);
        Date endTime = calendar.getTime();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(endTime));
        System.out.println("endTime: " + endTime.getTime());
    }
}
