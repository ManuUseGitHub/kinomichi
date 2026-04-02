package be.technifutur.kinomichi.M;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeTables {
    private List<TimeTable> timeTables;

    public TimeTables() {

        this.timeTables = new ArrayList<>();
    }

    public void addTimeTable(TimeTable.Builder building) {
        AtomicInteger lastId = new AtomicInteger();
        timeTables.stream().mapToInt(TimeTable::getId).max().ifPresent(lastId::set);

        timeTables.add(building
                .setId(lastId.get()+1)
                .build());
    }

    public TimeTable getTimeTableById(int id) {
        return this.timeTables.stream().filter(t -> t.getId() == id).findFirst()
                .stream()
                .toList()
                .getFirst();
    }

    public void removeTimetable(int id) {
        this.timeTables.removeIf(t -> t.getId() == id);
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }
}
