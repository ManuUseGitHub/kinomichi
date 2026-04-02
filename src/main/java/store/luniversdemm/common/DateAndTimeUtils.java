package store.luniversdemm.common;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class DateAndTimeUtils {
    public static int[] maxMonthDays(int year) {
        return IntStream.range(0, 12)
                .map(month -> maxDaysOfMonth(year, month+1))
                .toArray();
    }

    public static boolean isLeapYear(int year) {

        // is a year a multiple of 4 ?
        return year % 4 == 0 &&
                // is it multiple of 100 ?
                year % 100 == 0 &&
                // is it a multiple of 400 ?
                year % 400 == 0;
    }

    public static int maxDaysOfMonth(int year, int month) {
        return month == 2 ? maxDaysOfFeb(year) :
                month == 4 || month == 6 || month == 9 || month == 11 ? 30 : 31;
    }

    public static int minutesFromLocalDate(LocalTime lTime){
        int hoursInMunites = lTime.getHour() * 60;
        return lTime.getMinute() + hoursInMunites;
    }

    public static int minutesFromMinutesOrTime(String minutesOrTime){
        LocalTime lTime = timeFromString(minutesOrTime);
        int hoursInMunites = lTime.getHour() * 60;
        return lTime.getMinute() + hoursInMunites;
    }

    private static int maxDaysOfFeb(int year) {
        return isLeapYear(year) ? 29 : 28;
    }

    public static LocalTime timeFromString(String minutesOrTime){
        AtomicReference<LocalTime> lTime = new AtomicReference<>(LocalTime.of(0,0));
        Utils.onMatch("^(?:(?<hours>\\d{1,2}):)?(?<minutes>\\d+)$", minutesOrTime, m -> {

            if(m.group("hours") == null){
                int totalMinutes = Integer.parseInt(m.group("minutes"));
                lTime.set(LocalTime.of(
                        totalMinutes / 60,
                        totalMinutes % 60
                ));
            } else {
                lTime.set(LocalTime.of(
                        Math.min(Integer.parseInt(m.group("hours")), 23),
                        Math.min(Integer.parseInt(m.group("minutes")), 59)
                ));
            }
        });
        return lTime.get();
    }
}
