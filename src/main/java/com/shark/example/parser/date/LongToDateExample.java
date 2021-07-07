package com.shark.example.parser.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LongToDateExample {
    public static void main(String[] argv) {
        long time = 1619001960105L;
        Date date = new Date();
        date.setTime(time);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(date));
    }
}
