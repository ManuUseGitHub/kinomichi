package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import static be.technifutur.kinomichi.S.NavigatorUtils.getBackOnSuccess;

public class ListingPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public ListingPlageMS(TimeTables tts, String event) {
        super(event);
        this.tts = tts;
    }


    @Override
    public IEventListener handle() {
        return event -> {
            tts.getItems().forEach(System.out::println);
            System.out.println("Faites la touche <Enter> pour continuer");

            getBackOnSuccess(this);
        };
    }
}
