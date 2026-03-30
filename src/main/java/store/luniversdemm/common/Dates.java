package store.luniversdemm.common;

import java.util.stream.IntStream;

public class Dates {
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

    private static int maxDaysOfFeb(int year) {
        return isLeapYear(year) ? 29 : 28;
    }
}
