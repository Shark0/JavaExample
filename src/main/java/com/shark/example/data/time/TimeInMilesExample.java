package com.shark.example.data.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeInMilesExample {
    public static void main(String[] argv) {
        System.out.println(new Date().getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1701164374634L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
