package be.technifutur.kinomichi.M;

import store.luniversdemm.common.DateAndTimeUtils;
import store.luniversdemm.common.Utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class TimeTable implements Serializable {
    private int id;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String animator;
    private String description;
    private String activity;

    private TimeTable() {
    }

    public String getDescription() {
        return this.description;
    }

    public String getActivity() {
        return this.activity;
    }

    public static class Builder {
        private String activity;
        private String description;

        // date
        private LocalDate date;

        private LocalTime start;
        private LocalTime end;

        private String animator;
        private int id;

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

        public Builder animator(String animator){
            this.animator = animator;
            return this;
        }

        public Builder description(String description){
            this.description = description;
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
            built.id = this.id;
            built.start = this.start;
            built.end = this.end;
            built.date = this.date;
            built.description = this.description;
            built.activity = this.activity;
            built.animator = this.animator;
            return built;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
    }

    @Override
    public String toString(){
        return """
                >Tranche horaire n° (%d)
                >-------------------------------------------------------------------------
                >Activité : %s
                >Description :
                > %s
                >Qui anime : %s
                >Temporalité ------------------------------------------------------------
                >%s | %s -> %s
                """.formatted(
                        getId(),getActivity(),getDescription(),getAnimator(),
                getDate().getDayOfMonth()+"/"+getDate().getMonthValue()+"/"+getDate().getYear(),
                getStart().getHour()+":"+getStart().getMinute(),
                getEnd().getHour()+":"+getEnd().getMinute());
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