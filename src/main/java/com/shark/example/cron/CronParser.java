package com.shark.example.cron;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class CronParser {
    private final String expression;

    @Nullable
    private final TimeZone timeZone;
    private final BitSet months;
    private final BitSet daysOfMonth;
    private final BitSet daysOfWeek;
    private final BitSet hours;
    private final BitSet minutes;

    public CronParser(String expression) {
        this(expression, TimeZone.getDefault());
    }

    public CronParser(String expression, TimeZone timeZone) {
        this.months = new BitSet(12);
        this.daysOfMonth = new BitSet(31);
        this.daysOfWeek = new BitSet(7);
        this.hours = new BitSet(24);
        this.minutes = new BitSet(60);
        this.expression = expression;
        this.timeZone = timeZone;
        this.parse(expression);
    }

    private CronParser(String expression, String[] fields) {
        this.months = new BitSet(12);
        this.daysOfMonth = new BitSet(31);
        this.daysOfWeek = new BitSet(7);
        this.hours = new BitSet(24);
        this.minutes = new BitSet(60);
        this.expression = expression;
        this.timeZone = null;
        this.doParse(fields);
    }

    String getExpression() {
        return this.expression;
    }

    public Date next(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(this.timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        long originalTimestamp = calendar.getTimeInMillis();
        this.doNext(calendar, calendar.get(1));
        if (calendar.getTimeInMillis() == originalTimestamp) {
            calendar.add(Calendar.MINUTE, 1);
            this.doNext(calendar, calendar.get(1));
        }

        return calendar.getTime();
    }

    private void doNext(Calendar calendar, int dot) {
        List<Integer> resets = new ArrayList();

        int minute = calendar.get(Calendar.MINUTE);
        int updateMinute = this.findNext(this.minutes, minute, calendar, Calendar.MINUTE, Calendar.HOUR_OF_DAY, resets);
        if (minute == updateMinute) {
            resets.add(Calendar.MINUTE);
        } else {
            this.doNext(calendar, dot);
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int updateHour = this.findNext(this.hours, hour, calendar, Calendar.HOUR_OF_DAY, Calendar.DAY_OF_WEEK, resets);
        if (hour == updateHour) {
            resets.add(Calendar.HOUR_OF_DAY);
        } else {
            this.doNext(calendar, dot);
        }

        int dayOfWeek = calendar.get(7);
        int dayOfMonth = calendar.get(5);
        int updateDayOfMonth = this.findNextDay(calendar, this.daysOfMonth, dayOfMonth, this.daysOfWeek, dayOfWeek, resets);
        if (dayOfMonth == updateDayOfMonth) {
            resets.add(Calendar.DATE);
        } else {
//            System.out.println("doNext dayOfMonth: " + dayOfMonth + ", updateDayOfMonth: " + updateDayOfMonth + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doNext(calendar, dot);
        }

        int month = calendar.get(Calendar.MONTH);
        int updateMonth = this.findNext(this.months, month, calendar, Calendar.MONTH, Calendar.YEAR, resets);
        if (month != updateMonth) {
            if (calendar.get(Calendar.YEAR) - dot > 4) {
                throw new IllegalArgumentException("Invalid cron expression \"" + this.expression + "\" led to runaway search for next trigger");
            }
//            System.out.println("doNext month: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doNext(calendar, dot);
        }

    }

    private int findNextDay(Calendar calendar, BitSet daysOfMonth, int dayOfMonth, BitSet daysOfWeek, int dayOfWeek, List<Integer> resets) {
        int count = 0;
        short max = 366;

        while ((!daysOfMonth.get(dayOfMonth) || !daysOfWeek.get(dayOfWeek - 1)) && count++ < max) {
            calendar.add(5, 1);
            dayOfMonth = calendar.get(5);
            dayOfWeek = calendar.get(7);
            this.reset(calendar, resets);
        }

        if (count >= max) {
            throw new IllegalArgumentException("Overflow in day for expression \"" + this.expression + "\"");
        } else {
            return dayOfMonth;
        }
    }

    private int findNext(BitSet bits, int value, Calendar calendar, int field, int nextField, List<Integer> lowerOrders) {
        int nextValue = bits.nextSetBit(value);
        if (nextValue == -1) {
            calendar.add(nextField, 1);
            this.reset(calendar, Collections.singletonList(field));
            nextValue = bits.nextSetBit(0);
        }

        if (nextValue != value) {
            calendar.set(field, nextValue);
            this.reset(calendar, lowerOrders);
        }

        return nextValue;
    }

    private void reset(Calendar calendar, List<Integer> fields) {
        Iterator var3 = fields.iterator();

        while (var3.hasNext()) {
            int field = (Integer) var3.next();
            calendar.set(field, field == Calendar.DATE ? 1 : 0);
        }
    }

    private void parse(String expression) throws IllegalArgumentException {
        String[] fields = StringUtils.tokenizeToStringArray(expression, " ");
        if (!areValidCronFields(fields)) {
            throw new IllegalArgumentException(String.format("Cron expression must consist of 5 fields (found %d in \"%s\")", fields.length, expression));
        } else {
            this.doParse(fields);
        }
    }

    public Date previous(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(this.timeZone);
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        long originalTimestamp = calendar.getTimeInMillis();
        this.doPrevious(calendar, calendar.get(Calendar.YEAR));
        if (calendar.getTimeInMillis() == originalTimestamp) {
            calendar.add(Calendar.MINUTE, -1);
            this.doPrevious(calendar, calendar.get(Calendar.YEAR));
        }

        return calendar.getTime();
    }

    private void doPrevious(Calendar calendar, int dot) {
        int month = calendar.get(Calendar.MONTH);
        List<Integer> resets = new ArrayList();

        int updateMonth = this.findPreview(this.months, month, calendar, Calendar.MONTH, Calendar.YEAR,
                List.of(Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE));

        if (month != updateMonth) {
            if (calendar.get(Calendar.YEAR) - dot > 4) {
                throw new IllegalArgumentException("Invalid cron expression \"" + this.expression + "\" led to runaway search for next trigger");
            }
//            System.out.println("doPreview month: " + month + ", update month: " + updateMonth + ", calendar " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doPrevious(calendar, dot);
            return;
        }

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int updateDayOfMonth = this.findPreviewDay(calendar, this.daysOfMonth, dayOfMonth, this.daysOfWeek, dayOfWeek, resets);
//        System.out.println("doPreview dayOfWeek: " + dayOfWeek + ", dayOfMonth: " + dayOfMonth + ", updateDayOfMonth: " + updateDayOfMonth);
        if (dayOfMonth != updateDayOfMonth) {
//            System.out.println("doPreview dayOfMonth: " + dayOfMonth + ", updateDayOfMonth: " + updateDayOfMonth + ", calendar: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doPrevious(calendar, dot);
            return;
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int updateHour = this.findPreview(this.hours, hour, calendar, Calendar.HOUR_OF_DAY, Calendar.DAY_OF_WEEK,
                List.of(Calendar.MINUTE));
        if (hour != updateHour) {
//            System.out.println("doPreview hour: " + hour + ", updateHour: " + updateHour + ", calendar: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doPrevious(calendar, dot);
            return;
        }

        int minute = calendar.get(Calendar.MINUTE);
        int updateMinute = this.findPreview(this.minutes, minute, calendar, Calendar.MINUTE, Calendar.HOUR_OF_DAY, resets);
        if (minute != updateMinute) {
//            System.out.println("doPreview minute: " + minute + ", updateMinute: " + updateMinute + ", calendar: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ").format(calendar.getTime()));
            this.doPrevious(calendar, dot);
        }
    }

    private int findPreviewDay(Calendar calendar, BitSet daysOfMonth, int dayOfMonth, BitSet daysOfWeek, int dayOfWeek, List<Integer> resets) {
//        System.out.println("findPreviewDay daysOfMonth: " + dayOfMonth + ", daysOfMonth: " + daysOfMonth.get(dayOfMonth) +
//                ", dayOfWeek: " + dayOfWeek + ", daysOfWeek: " + daysOfWeek.get(dayOfWeek -1)) ;
        int count = 0;
        short max = 366;

        while ((!daysOfMonth.get(dayOfMonth) || !daysOfWeek.get(dayOfWeek - 1)) && count++ < max) {
            calendar.add(Calendar.DATE, -1);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//            System.out.println("findPreviewDay daysOfMonth: " + dayOfMonth + ", dayOfWeek: " + dayOfWeek) ;
            this.resetPreviewDay(calendar, resets);
        }

        if (count >= max) {
            throw new IllegalArgumentException("Overflow in day for expression \"" + this.expression + "\"");
        } else {
            return dayOfMonth;
        }
    }

    private void resetPreviewDay(Calendar calendar, List<Integer> fields) {
        Iterator var3 = fields.iterator();

        while (var3.hasNext()) {
            int field = (Integer) var3.next();
            calendar.set(field, field == Calendar.DAY_OF_MONTH ? 1 : 0);
        }
        //將其他小的時間filed 補成最大值 - Shark
//        calendar.add(Calendar.DAY_OF_WEEK, 1);
//        calendar.add(Calendar.SECOND, -1);
//        System.out.println("resetPreviewDay: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime())) ;
    }

    private int findPreview(BitSet bits, int value, Calendar calendar, int field, int nextFiledId, List<Integer> lowerOrders) {

        int previewValue = bits.previousSetBit(value);
        if (previewValue == -1) {
            //比cron tab的 preview 值小，取cron tab裡面最大的值

            this.resetPreview(calendar, field, Collections.singletonList(field));
            //找出bits的最大值

            previewValue = bits.previousSetBit(Integer.MAX_VALUE);
            //將下一個filed，-1
            calendar.add(nextFiledId, -1);
        }

        if (previewValue != value) {
            //替換
            calendar.set(field, previewValue);
//            System.out.println("findPreview replace filed: " + field + ", original: " + value +
//                    ", replace value: " + previewValue +
//                    ", final date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime())) ;
            this.resetPreview(calendar, field, lowerOrders);
        }

        return previewValue;
    }

    private void resetPreview(Calendar calendar, Integer currentField, List<Integer> fields) {
        Iterator var3 = fields.iterator();

        while (var3.hasNext()) {
            int field = (Integer) var3.next();
            calendar.set(field, field == Calendar.DAY_OF_MONTH ? 1 : 0);
        }
        //將其他小的時間filed 補成最大值 - Shark
        calendar.add(currentField, 1);
        calendar.add(Calendar.MINUTE, -1);
    }

    private void doParse(String[] fields) {
        this.setNumberHits(this.minutes, fields[0], 0, 60);
        this.setNumberHits(this.hours, fields[1], 0, 24);
        this.setDaysOfMonth(this.daysOfMonth, fields[2]);
        this.setMonths(this.months, fields[3]);
        this.setDays(this.daysOfWeek, this.replaceOrdinals(fields[4], "SUN,MON,TUE,WED,THU,FRI,SAT"), 8);
        if (this.daysOfWeek.get(7)) {
            this.daysOfWeek.set(0);
            this.daysOfWeek.clear(7);
        }
    }

    private String replaceOrdinals(String value, String commaSeparatedList) {
        String[] list = StringUtils.commaDelimitedListToStringArray(commaSeparatedList);

        for (int i = 0; i < list.length; ++i) {
            String item = list[i].toUpperCase();
            value = StringUtils.replace(value.toUpperCase(), item, "" + i);
        }

        return value;
    }

    private void setDaysOfMonth(BitSet bits, String field) {
        int max = 31;
        this.setDays(bits, field, max + 1);
        bits.clear(0);
    }

    private void setDays(BitSet bits, String field, int max) {
        if (field.contains("?")) {
            field = "*";
        }

        this.setNumberHits(bits, field, 0, max);
    }

    private void setMonths(BitSet bits, String value) {
        int max = 12;
        value = this.replaceOrdinals(value, "FOO,JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV,DEC");
        BitSet months = new BitSet(13);
        this.setNumberHits(months, value, 1, max + 1);

        for (int i = 1; i <= max; ++i) {
            if (months.get(i)) {
                bits.set(i - 1);
            }
        }
    }

    private void setNumberHits(BitSet bits, String value, int min, int max) {
        String[] fields = StringUtils.delimitedListToStringArray(value, ",");
        String[] var6 = fields;
        int var7 = fields.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            String field = var6[var8];
            if (!field.contains("/")) {
                int[] range = this.getRange(field, min, max);
                bits.set(range[0], range[1] + 1);
            } else {
                String[] split = StringUtils.delimitedListToStringArray(field, "/");
                if (split.length > 2) {
                    throw new IllegalArgumentException("Incrementer has more than two fields: '" + field + "' in expression \"" + this.expression + "\"");
                }

                int[] range = this.getRange(split[0], min, max);
                if (!split[0].contains("-")) {
                    range[1] = max - 1;
                }

                int delta = Integer.parseInt(split[1]);
                if (delta <= 0) {
                    throw new IllegalArgumentException("Incrementer delta must be 1 or higher: '" + field + "' in expression \"" + this.expression + "\"");
                }

                for (int i = range[0]; i <= range[1]; i += delta) {
                    bits.set(i);
                }
            }
        }

    }

    private int[] getRange(String field, int min, int max) {
        int[] result = new int[2];
        if (field.contains("*")) {
            result[0] = min;
            result[1] = max - 1;
            return result;
        } else {
            if (!field.contains("-")) {
                result[0] = result[1] = Integer.parseInt(field);
            } else {
                String[] split = StringUtils.delimitedListToStringArray(field, "-");
                if (split.length > 2) {
                    throw new IllegalArgumentException("Range has more than two fields: '" + field + "' in expression \"" + this.expression + "\"");
                }

                result[0] = Integer.parseInt(split[0]);
                result[1] = Integer.parseInt(split[1]);
            }

            if (result[0] < max && result[1] < max) {
                if (result[0] >= min && result[1] >= min) {
                    if (result[0] > result[1]) {
                        throw new IllegalArgumentException("Invalid inverted range: '" + field + "' in expression \"" + this.expression + "\"");
                    } else {
                        return result;
                    }
                } else {
                    throw new IllegalArgumentException("Range less than minimum (" + min + "): '" + field + "' in expression \"" + this.expression + "\"");
                }
            } else {
                throw new IllegalArgumentException("Range exceeds maximum (" + max + "): '" + field + "' in expression \"" + this.expression + "\"");
            }
        }
    }

    public static boolean isValidExpression(@Nullable String expression) {
        if (expression == null) {
            return false;
        } else {
            String[] fields = StringUtils.tokenizeToStringArray(expression, " ");
            if (!areValidCronFields(fields)) {
                return false;
            } else {
                try {
                    new CronParser(expression, fields);
                    return true;
                } catch (IllegalArgumentException var3) {
                    return false;
                }
            }
        }
    }

    private static boolean areValidCronFields(@Nullable String[] fields) {
        return fields != null && fields.length == 5;
    }


    public int hashCode() {
        return 17 * this.months.hashCode() + 29 * this.daysOfMonth.hashCode() + 37 * this.daysOfWeek.hashCode() + 41 * this.hours.hashCode() + 53 * this.minutes.hashCode();
    }

    public void print() {
        System.out.println("months: " + months + ", daysOfMonth: " + daysOfMonth + ", daysOfWeek: " +
                daysOfWeek + ", hours: " + hours + ", minutes: " + minutes);
    }

    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.expression;
    }
}
