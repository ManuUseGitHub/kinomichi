package be.technifutur.kinomichi.M;

import store.luniversdemm.common.Dates;
import store.luniversdemm.common.Utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class TimeTable {
    private int id;
    private LocalDate date;
    private String startHour;
    private String endHour;
    private String animator;

    private TimeTable() {
    }

    public static class Builder {
        private String activity;
        private String description;

        // date
        private LocalDate date;

        private int startHour;
        private int duration;

        public Builder(String activity){
            this.activity = activity;
        }

        public Builder date(String dateFormated){
            Utils.onMatch("(?<day>\\d{1,2})\\/(?<month>\\d{1,2})\\/(?<year>\\d{4})",dateFormated,m -> {
                int year = Integer.parseInt(m.group("year"));
                int month = Math.max(1,Integer.parseInt(m.group("month")));
                int inputDay = Math.max(1,Integer.parseInt(m.group("day")));
                int dayOfMonth = Dates.maxMonthDays(year)[month-1];
                this.date = LocalDate.of(year,
                        Math.min(month,12),
                        Math.min(inputDay,dayOfMonth));
            });

            return this;
        }

        public TimeTable build(){
            TimeTable built = new TimeTable();
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

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getAnimator() {
        return animator;
    }

    public void setAnimator(String animator) {
        this.animator = animator;
    }
    //endregion
}