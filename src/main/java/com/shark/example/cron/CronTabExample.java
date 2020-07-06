package com.shark.example.cron;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CronTabExample {
    public static void main(String[] argv) {
        List<String> cronTabList = new ArrayList<>();
//        cronTabList.add("*/5 * * * * *");
//        cronTabList.add("0 */15 * * * *");
//        cronTabList.add("0 5 * * * *");
//        cronTabList.add("0 5 1 * * *");
//        cronTabList.add("0 0 0 1 * *");
//        cronTabList.add("0 0 0 1 1 *");
        cronTabList.add("0 */15 0 25 12 *");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        Date currentTime = new Date();
        int index = 0;
        for (String cronTab : cronTabList) {
            CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cronTab);
//            cronSequenceGenerator.print();

            Date nextDate = cronSequenceGenerator.next(currentTime);
            System.out.println("index: index" + index + ", nextDate: " +
                    nextDate.toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));

            Date previewDate = cronSequenceGenerator.preview(currentTime);
            System.out.println("index: index" + index + ", prevDate: " +
                    previewDate.toInstant().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
            index = index + 1;
        }
    }
}
