package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

public class ListingPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public ListingPlageMS(TimeTables tts,String event) {
        super(event);
        this.tts = tts;
    }

    public IEventListener handle() {
        return event -> {
            Promptor.getMenu();

            tts.getTimeTables().forEach(System.out::println);
            System.out.println("Faites la touche <Enter> pour continuer");
            Saisir.scanString();

            EventBus.publishEvent("FINISH:ACTIVITY", Event.createAddEvent(this));

        };
    }
}
