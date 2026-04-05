package be.technifutur.kinomichi.M;

import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.CopyCatAble;
import store.luniversdemm.common.DateAndTimeUtils;
import store.luniversdemm.common.Utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class TimeTable implements CopyCatAble<TimeTable,TimeTable.Builder>, Serializable {
    private int id;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private String animator;
    private String description;
    private String activity;

    private TimeTable() {
    }

    @Override
    public void copyCat(TimeTable built) {
        if (id != built.id)
            throw new RuntimeException("ID mismatched");
        this.id = built.id;
        this.date = built.date;
        this.end = built.end;
        this.start = built.start;
        this.animator = built.animator;
        this.description = built.description;
        this.activity = built.activity;
    }

    @Override
    public TimeTable.Builder pastyCat(Builder built) {
        built.id = this.id;
        built.date = this.date;
        built.end = this.end;
        built.start = this.start;
        built.animator = this.animator;
        built.description = this.description;
        built.activity = this.activity;
        return built;
    }

    public static class Builder {
        public String activity;
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

        public Builder activity(String activity) {
            this.activity = activity;
            return this;
        }

        public Builder animator(String animator) {
            this.animator = animator;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder start(String time) {
            this.start = DateAndTimeUtils.timeFromString(time);
            return this;
        }

        public Builder duration(String minutesOrTime) {
            if (this.start != null) {
                int hoursInMinutes = DateAndTimeUtils
                        .minutesFromLocalDate(this.start) + DateAndTimeUtils
                        .minutesFromMinutesOrTime(minutesOrTime);

                this.end = LocalTime.of(
                        (hoursInMinutes / 60) % 24,
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

        public Builder id(int id) {
            this.id = id;
            return this;
        }
    }

    @Override
    public String toString() {
        return """
                >Tranche horaire n° (%s)
                >-------------------------------------------------------------------------
                >Activité : %s
                >Description :
                > %s
                >Qui anime : %s
                >Temporalité ------------------------------------------------------------
                >%s | %s -> %s
                """.formatted(
                ConsoleColors.BLUE + getId() + ConsoleColors.RESET, ConsoleColors.BLUE + getActivity() + ConsoleColors.RESET, ConsoleColors.BLUE + getDescription() + ConsoleColors.RESET, ConsoleColors.BLUE + getAnimator() + ConsoleColors.RESET,
                ConsoleColors.BLUE + getDate().getDayOfMonth() + "/" + getDate().getMonthValue() + "/" + getDate().getYear() + ConsoleColors.RESET,
                ConsoleColors.GREEN + getStart().getHour() + ":" + getStart().getMinute() + ConsoleColors.RESET,
                ConsoleColors.RED + getEnd().getHour() + ":" + getEnd().getMinute() + ConsoleColors.RESET);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivity() {
        return activity;
    }

    public String getAnimator() {
        return animator;
    }

    public void setAnimator(String animator) {
        this.animator = animator;
    }
    //endregion
}