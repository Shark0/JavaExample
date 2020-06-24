package com.shark.example.parser.date;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PatternWarehouse {

    public static final Pattern booleanPattern = Pattern.compile(
            "^(?:y|n|yes|no|true|false|1|0|f|t|是|否|有|無|无|沒有|没有)$",
            Pattern.CASE_INSENSITIVE);

    public static final List<String> datePatternList = Arrays.asList(
            "y M", "y-M", "y/M",
            "y M d", "y-M-d", "y/M/d",
            "d M y", "d-M-y", "d/M/y",
            "M d y", "M-d-y", "M/d/y",
            "y-M-d H:m:s", "d-M-y-H:m:s", "d-M-y-H:m:s-a",
            "y-M-d H:m:s.SSS", "y-M-d H:m:s-SSS",
            "y-M-d H:m:s.SSSSSSSSS", "y-M-d H:m:s-SSSSSSSSS",
            "y/M/d H:m", "M/d/y H:m",
            "d/m-y H:m:s", "d.m.y H:m:s", "d/M/y H:m:s",
            "M d,y H:m:s a", "M d,y h:m:s a", "d/M/y,H:m:s a",
            "d/M/y,h:m:s a", "d/M/y H:m:s a", "d/M/y h:m:s a",
            "y年M月d日H時m分s秒", "y年 M月 d日 H時 m分 s秒", "y 年 M 月 d 日 H 時 m 分 s 秒",
            "y年M月d日H时m分s秒", "y年 M月 d日 H时 m分 s秒", "y 年 M 月 d 日 H 时 m 分 s 秒"
    );
}