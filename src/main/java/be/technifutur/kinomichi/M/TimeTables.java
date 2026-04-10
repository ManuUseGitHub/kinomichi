package be.technifutur.kinomichi.M;

import be.technifutur.kinomichicommon.interfaces.GroupManaging;

import java.beans.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeTables implements GroupManaging<TimeTable, TimeTable.Builder>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<TimeTable> timeTables;

    public TimeTables() {
        this.timeTables = new ArrayList<>();
    }

    @Transient
    @Override
    public void addItem(TimeTable.Builder building) {
        AtomicInteger lastId = new AtomicInteger();
        timeTables.stream().mapToInt(TimeTable::getId).max().ifPresent(lastId::set);

        timeTables.add(building
                .id(lastId.get()+1)
                .build());
    }

    @Transient
    @Override
    public TimeTable getItemById(int id) {
        return this.timeTables.stream().anyMatch(t -> t.getId() == id) ?
                this.timeTables.stream().filter(t -> t.getId() == id)
                        .findFirst()
                        .get() :
                null;
    }

    @Transient
    @Override
    public void removeItem(int id) {
        this.timeTables.removeIf(t -> t.getId() == id);
    }

    @Transient
    @Override
    public List<TimeTable> getItems() {
        return this.timeTables;
    }

    @Transient
    @Override
    public void replaceItems(Object load) {
        this.timeTables.clear();
        this.timeTables.addAll(((TimeTables)load).getItems());
    }

    @Transient
    @Override
    public void changeItem(TimeTable.Builder builder, TimeTable selected) {
        selected.copyCat(builder.id(selected.getId()).build());
    }

}
