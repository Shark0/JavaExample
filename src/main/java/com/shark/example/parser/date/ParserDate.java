package com.shark.example.parser.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParserDate {

    public static void main(String[] argv) {
//        String dateString = "2018年12月12日";
        String dateString = "2018-12-12";
//        String dateString = "2018年第1周";
//        String dateString = "2018-W1";
//        String dateString = "2017年第2季";
//        String dateString = "2017-Q2";
        String result = parserDateTime(dateString);
        System.out.println("result: " + result);
    }


    private static String parserDateTime(String dateTime) {
        //處理周
        if(dateTime.contains("周")) {
            //y年第w周
            String yearString = dateTime.substring(0, dateTime.indexOf("年"));
            int year = Integer.parseInt(yearString);
            String weekString = dateTime.substring(dateTime.indexOf("年第") + 2, dateTime.indexOf("周"));
            int week = Integer.parseInt(weekString);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("date: " + calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
            return String.valueOf(calendar.getTime().getTime());
        }
        if(dateTime.contains("-W")) {
            //y-'W'w
            String yearString = dateTime.substring(0, dateTime.indexOf("-W"));
            int year = Integer.parseInt(yearString);
            String weekString = dateTime.substring(dateTime.indexOf("-W") + 2);
            int week = Integer.parseInt(weekString);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("date: " + calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
            return String.valueOf(calendar.getTime().getTime());
        }

        //處理季
        if(dateTime.contains("季")) {
            //y年第q季
            String yearString = dateTime.substring(0, dateTime.indexOf("年"));
            int year = Integer.parseInt(yearString);
            String quarterString = dateTime.substring(dateTime.indexOf("年第") + 2, dateTime.indexOf("季"));
            int quarter = Integer.parseInt(quarterString);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, quarter * 3);
            calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("date: " + calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
            return String.valueOf(calendar.getTime().getTime());
        }
        if(dateTime.contains("-Q")) {
            //y-'Q'q
            String yearString = dateTime.substring(0, dateTime.indexOf("-Q"));
            int year = Integer.parseInt(yearString);
            String quarterString = dateTime.substring(dateTime.indexOf("-Q") + 2);
            int quarter = Integer.parseInt(quarterString);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, quarter * 3);
            calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("date: " + calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
            return String.valueOf(calendar.getTime().getTime());
        }

        //處理其他格式
        List<String> formatList = new ArrayList<>();
        formatList.add("y年M月d日 H點m分s秒SSS毫秒");
        formatList.add("y年M月d日 H点m分s秒SSS毫秒");
        formatList.add("y-M-dd H:M:ss.SSS");
        formatList.add("y年M月d日 H點M分ss秒");
        formatList.add("y年M月d日 H点M分ss秒");
        formatList.add("y-M-d H:M:ss");
        formatList.add("y年M月d日 H點M分");
        formatList.add("y年M月d日 H点M分");
        formatList.add("y-M-dd H:M");
        formatList.add("y年M月d日 H點");
        formatList.add("y年M月d日 H点");
        formatList.add("y-M-d H");
        formatList.add("y年M月d日");
        formatList.add("y年M月d日");
        formatList.add("y-M-d");
        formatList.add("y年M月");
        formatList.add("y年M月");
        formatList.add("y-M");
        formatList.add("y年");
        formatList.add("y年");
        formatList.add("y");

        for (String format : formatList) {
            DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendPattern(format)
                    .parseDefaulting(ChronoField.ERA, 1)
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
                Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                System.out.println("date: " + date.toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
                return String.valueOf(date.getTime());
            } catch (Exception e) {
            }
        }

        return "";
    }
}
