package be.technifutur.kinomichi.S.microServices.plages;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.PlageVersionManagerService;
import be.technifutur.kinomichi.S.microServices.MicroService;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;
import store.luniversdemm.common.Saisir;

import static store.luniversdemm.common.Utils.onReadTextFile;

public class LoadPlageMS extends MicroService implements MicroServiable {
    private final TimeTables tts;

    public LoadPlageMS(TimeTables tts,String event){
        super(event);
        this.tts = tts;
    }

    public LoadPlageMS(TimeTables tts,String ... events){
        super(events);
        this.tts = tts;
    }

    public IEventListener handle() {
        return new IEventListener() {
            @Override
            public void processEvent(Event event) {
                Promptor.getMenu();

                System.out.println("Entrez le chemin vers un fichier");
                String fileName = Saisir.scanString();

                PlageVersionManagerService pvms = new PlageVersionManagerService();
                if(StateEngine.getInstance().getCurrentState() == States.PLAGE_LOADING_A){

                    tts.replaceTimeTables(pvms.load(fileName));

                    EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
                } else{
                    onReadTextFile(text -> {
                        tts.replaceTimeTables(pvms.loadByTextSource(text));

                        EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(this));
                    });
                }
            }
        };
    }
}
