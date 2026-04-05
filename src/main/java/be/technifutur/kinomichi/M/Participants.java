package be.technifutur.kinomichi.M;

import be.technifutur.kinomichicommon.interfaces.GroupManaging;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Participants implements GroupManaging<Participant, Participant.Builder>, Serializable {
    private final List<Participant> participants;

    public Participants() {
        this.participants = new ArrayList<>();
    }

    @Transient
    @Override
    public void addItem(Participant.Builder building) {
        AtomicInteger lastId = new AtomicInteger();
        participants.stream().mapToInt(Participant::getId).max().ifPresent(lastId::set);

        participants.add(building
                .id(lastId.get()+1)
                .build());
    }

    @Transient
    @Override
    public Participant getItemById(int id) {
        return this.participants.stream().anyMatch(p -> p.getId() == id) ?
                this.participants.stream().filter(p -> p.getId() == id)
                        .findFirst()
                        .get() :
                null;
    }

    @Transient
    @Override
    public void removeItem(int id) {
        this.participants.removeIf(t -> t.getId() == id);
    }

    @Transient
    @Override
    public List<Participant> getItems() {
        return this.participants;
    }

    @Transient
    @Override
    public void replaceItems(Object load) {
        this.participants.clear();
        this.participants.addAll(((Participants)load).getItems());
    }

    @Transient
    @Override
    public void changeItem(Participant.Builder builder, Participant selected) {
        selected.copyCat(builder.id(selected.getId()).build());
    }
}
