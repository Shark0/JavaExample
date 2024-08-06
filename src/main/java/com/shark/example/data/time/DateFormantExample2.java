package com.shark.example.data.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormantExample2 {
    public static void main(String[] argv) throws ParseException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = "7/4/24";
        Date date = simpleDateFormat1.parse(dateString);
        dateString = simpleDateFormat2.format(date);
        System.out.println(dateString);
    }
}
