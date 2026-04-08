package be.technifutur.kinomichi.S.microServices.people;

import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

import static be.technifutur.kinomichi.V.Promptor.selectItems;

public class DeletePeopleMS extends MicroService implements MicroServiable {
    private final Participants pps;

    public DeletePeopleMS(Participants tts, String event){
        super(event);
        this.pps = tts;
    }

    @Override
    public IEventListener handle() {
        return event -> {
            onDeletePeople(event);
            EventBus.publishEvent(Event.Topic.NAVIGATION.name(), Event.createBackNavEvent(this));
            EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
        };
    }

    public void onDeletePeople(Event event) {
        Promptor.displayMenu();
        selectItems(pps,"Quelle participants voulez-vous supprimer? (plusieurs choix possibles)")
                .stream().map(Integer::parseInt).sorted((a, b) -> b - a)
                .forEach(pps::removeItem);
    }
}
