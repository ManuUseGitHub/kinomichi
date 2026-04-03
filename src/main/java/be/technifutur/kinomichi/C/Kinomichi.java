package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.S.microServices.plages.*;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.Savable;
import store.luniversdemm.common.Saisir;

public class Kinomichi implements Savable {

    private final StateEngine stateEngine;
    private final RequestTranslator requestTranslator;
    private boolean saved = true;
    private boolean doing;

    public Kinomichi(){
        this.requestTranslator = new RequestTranslator();
        stateEngine = StateEngine.getInstance();
        stateEngine.initStateEngine(States.MAIN_MENU);

        Promptor.setStateEngine(this.stateEngine);
    }

    public void run() {
        long data = 0;
        TimeTables tts = new TimeTables();
        new StarterMS(tts,"NAV:"+ States.MAIN_MENU.getValue());
        new AddPlageMS(tts,"NAV:" + States.PLAGE_ADDING_ACTIVITY.getValue());
        new ListingPlageMS(tts,"NAV:" + States.PLAGE_LISTING_ACTIVITY.getValue());
        new SavePlageMS(tts,"NAV:"+ States.PLAGE_SAVING_ACTIVITY.getValue());
        new EditPlageMS(tts,"NAV:" + States.PLAGE_EDIT_ACTIVITY.getValue());
        new LoadPlageMS(tts,
                "NAV:"+ States.PLAGE_LOADING_ACTIVITY_A.getValue(),
                "NAV:"+ States.PLAGE_LOADING_ACTIVITY_B.getValue());

        String current = null;

        EventBus.registerListener("FINISH:ACTIVITY", new IEventListener() {
            @Override
            public void processEvent(Event event) {
                doing = false;
                stateEngine.apply(Constants.BACK_CODE);
            }
        });
        EventBus.publishEvent(
                "NAV:"+States.MAIN_MENU.getValue(),
                Event.createNavEvent(this)
        );
        while(stateEngine.getCurrentState() != null) {
            if(!this.doing){
                // Only print AFTER state change
                Promptor.getMenu();
                Promptor.askWhatDo();
                data = this.requestTranslator.translate(Saisir.scanString(), this);
                current = stateEngine.getCurrentState().getValue();

                stateEngine.apply(data);
            }else{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(stateEngine.getCurrentState() != null && !current.equals(stateEngine.getCurrentState().getValue())){
                current = stateEngine.getCurrentState().getValue();
                if(stateEngine.getCurrentState().getValue().matches("b11|b13|b411|b412|b5|b31")){
                    doing = true;
                }
                EventBus.publishEvent(
                        "NAV:"+stateEngine.getCurrentState().getValue(),
                        Event.createNavEvent(this)
                );
            }
        }
    }

    @Override
    public boolean save() {
        this.saved = true;
        return saved;
    }

    @Override
    public boolean isSaved() {
        return saved;
    }

    @Override
    public void change() {
        this.saved = false;
    }
}
