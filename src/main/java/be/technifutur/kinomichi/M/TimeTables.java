package be.technifutur.kinomichi.M;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeTables implements Serializable {
    private List<TimeTable> timeTables;

    public TimeTables() {
        this.timeTables = new ArrayList<>();
    }

    @Transient
    public void addTimeTable(TimeTable.Builder building) {
        AtomicInteger lastId = new AtomicInteger();
        timeTables.stream().mapToInt(TimeTable::getId).max().ifPresent(lastId::set);

        timeTables.add(building
                .setId(lastId.get()+1)
                .build());
    }

    @Transient
    public TimeTable getTimeTableById(int id) {
        return this.timeTables.stream().filter(t -> t.getId() == id).findFirst()
                .stream()
                .toList()
                .getFirst();
    }

    @Transient
    public void removeTimetable(int id) {
        this.timeTables.removeIf(t -> t.getId() == id);
    }

    public List<TimeTable> getTimeTables() {
        return this.timeTables;
    }

    @Transient
    public void replaceTimeTables(TimeTables load) {
        this.timeTables.clear();
        this.timeTables.addAll(load.getTimeTables());
    }

    public void changeTimeTable(TimeTable.Builder builder, TimeTable selected) {
        selected.copyCat(builder.setId(selected.getId()).build());
    }
}
