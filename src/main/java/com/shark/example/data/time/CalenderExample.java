package com.shark.example.data.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalenderExample {
    public static void main(String[] argv) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar calendar = Calendar.getInstance();
        System.out.println("now: " + simpleDateFormat.format(calendar.getTime()));
        CalenderExample calenderExample = new CalenderExample();
        calenderExample.addSecond(simpleDateFormat, calendar, 1);
        calenderExample.setSecond(simpleDateFormat, calendar, 0);
        calenderExample.addMinute(simpleDateFormat, calendar, 1);
        calenderExample.setMinute(simpleDateFormat, calendar, 0);
        calenderExample.addHour(simpleDateFormat, calendar, 1);
        calenderExample.setHour(simpleDateFormat, calendar, 0);
        calenderExample.addDayOfMonth(simpleDateFormat, calendar, 1);
        calenderExample.setDayOfMonth(simpleDateFormat, calendar, 1);
        calenderExample.addDayOfYear(simpleDateFormat, calendar, 1);
        calenderExample.setDayOfYear(simpleDateFormat, calendar, 1);
        calenderExample.addMonth(simpleDateFormat, calendar, 1);
        calenderExample.setMonth(simpleDateFormat, calendar, 0);
        calenderExample.addYear(simpleDateFormat, calendar, 1);
        calenderExample.setYear(simpleDateFormat, calendar, 2023);
    }

    public void addSecond(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.SECOND, value);
        System.out.println("addSecond: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setSecond(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.SECOND, value);
        System.out.println("setSecond: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addMinute(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.MINUTE, value);
        System.out.println("addMinute: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setMinute(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.MINUTE, value);
        System.out.println("setMinute: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addHour(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.HOUR, value);
        System.out.println("addHour: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setHour(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.HOUR, value);
        System.out.println("setHour: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addDayOfYear(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.DAY_OF_YEAR, value);
        System.out.println("addDayOfYear: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setDayOfYear(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.DAY_OF_YEAR, value);
        System.out.println("setDayOfYear: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addDayOfMonth(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.DAY_OF_MONTH, value);
        System.out.println("addDayOfMonth: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setDayOfMonth(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.DAY_OF_MONTH, value);
        System.out.println("setDayOfMonth: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addMonth(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.MONTH, value);
        System.out.println("addMonth: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setMonth(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.MONTH, value);
        System.out.println("setMonth: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void addYear(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.add(Calendar.YEAR, value);
        System.out.println("addYear: " + simpleDateFormat.format(calendar.getTime()));
    }

    public void setYear(SimpleDateFormat simpleDateFormat, Calendar calendar, int value) {
        calendar.set(Calendar.YEAR, value);
        System.out.println("setYear: " + simpleDateFormat.format(calendar.getTime()));
    }
}
