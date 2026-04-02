package be.technifutur.kinomichi.M;

import store.luniversdemm.common.DateAndTimeUtils;
import store.luniversdemm.common.Utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeTable {
    private int id;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String animator;

    private TimeTable() {
    }

    public static class Builder {
        private String activity;
        private String description;

        // date
        private LocalDate date;

        private LocalTime start;
        private LocalTime end;

        public Builder(String activity) {
            this.activity = activity;
        }

        public Builder date(String dateFormated) {
            Utils.onMatch("(?<day>\\d{1,2})\\/(?<month>\\d{1,2})\\/(?<year>\\d{4})", dateFormated, m -> {
                int year = Integer.parseInt(m.group("year"));
                int month = Math.min(Math.max(1, Integer.parseInt(m.group("month"))), 12);
                int inputDay = Math.max(1, Integer.parseInt(m.group("day")));
                int dayOfMonth = DateAndTimeUtils.maxMonthDays(year)[month - 1];
                this.date = LocalDate.of(year,
                        month,
                        Math.min(inputDay, dayOfMonth));
            });

            return this;
        }

        public Builder start(String time) {
            this.start = DateAndTimeUtils.timeFromString(time);
            return this;
        }

        public Builder duration(String minutesOrTime) {
            if(this.start != null) {
                int hoursInMinutes = DateAndTimeUtils
                        .minutesFromLocalDate(this.start) + DateAndTimeUtils
                        .minutesFromMinutesOrTime(minutesOrTime);

                this.end = LocalTime.of(
                        (hoursInMinutes / 60) %24,
                        hoursInMinutes % 60
                );
            }
            return this;
        }

        public TimeTable build() {
            TimeTable built = new TimeTable();
            built.start = this.start;
            built.end = this.end;
            built.date = this.date;
            return built;
        }
    }

    //region Accessors / Mutators
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getAnimator() {
        return animator;
    }

    public void setAnimator(String animator) {
        this.animator = animator;
    }
    //endregion
}