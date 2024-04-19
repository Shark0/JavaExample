package com.shark.example.data.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class InstantExample {

    public static void main(String argv[]) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = Instant.ofEpochMilli(new Date().getTime());
        //zone id
        ZoneId taipeiZoneId = ZoneId.of("Asia/Taipei");
        ZoneId japanZoneId = ZoneId.of("Japan");
        System.out.println("instant taipeiZoneId format result: " + instant.atZone(taipeiZoneId).toLocalDateTime().format(dateTimeFormatter));
        System.out.println("instant japanZoneId format result: " + instant.atZone(japanZoneId).toLocalDateTime().format(dateTimeFormatter));
        System.out.println("instant format result: " + dateTimeFormatter.format(instant));
    }
}
