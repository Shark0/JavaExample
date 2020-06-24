package com.shark.example.parser.date;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class DateIdentificater {

    private final List<DateTimeFormatter> formatterList;

    public DateIdentificater() {
        formatterList = PatternWarehouse.datePatternList.stream()
                .map(pattern -> new DateTimeFormatterBuilder()
                        .appendPattern(pattern)
                        .parseDefaulting(ChronoField.ERA, 1)
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT))
                .collect(Collectors.toList());
    }

    public Result identify(String input, Set<String> datePatterns) {
        Result result = new Result();
        // check with existing datetime patterns
        for (String datePattern : datePatterns) {
            try {
                DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                        .appendPattern(datePattern)
                        .parseDefaulting(ChronoField.ERA, 1)
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                        .toFormatter()
                        .withResolverStyle(ResolverStyle.STRICT);
                LocalDateTime.parse(input, dateTimeFormatter);
                result.setDate(true);
                result.setDatePattern(datePattern);
                return result;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // check with non-existing datetime patterns
        for (int index = 0; index < formatterList.size(); index++) {
            DateTimeFormatter formatter = formatterList.get(index);
            try {
                LocalDateTime.parse(input, formatter);
                result.setDate(true);
                result.setDatePattern(PatternWarehouse.datePatternList.get(index));
                return result;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        result.setDate(false);
        return result;
    }

    @Data
    public static class Result {
        private boolean isDate;
        private String datePattern;
    }
}
