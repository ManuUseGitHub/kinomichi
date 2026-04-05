package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

public class ListingPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public ListingPlageMS(TimeTables tts,String event) {
        super(event);
        this.tts = tts;
    }


    @Override
    public IEventListener handle() {
        return new IEventListener() {
            @Override
            public void processEvent(Event event) {
                tts.getItems().forEach(System.out::println);
                System.out.println("Faites la touche <Enter> pour continuer");

                EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
            }
        };
    }
}
