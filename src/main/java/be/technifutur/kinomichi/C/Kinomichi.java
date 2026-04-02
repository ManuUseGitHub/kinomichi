package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.S.PlageService;
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
        new PlageService();

        String current = null;

        EventBus.registerListener("FINISH:ACTIVITY", new IEventListener() {
            @Override
            public void processEvent(Event event) {
                doing = false;
                stateEngine.apply(Constants.BACK_CODE);
            }
        });
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
                if(stateEngine.getCurrentState().getValue().matches("b11|b13")){
                    doing = true;
                }
                EventBus.publishEvent(
                        "NAV:"+stateEngine.getCurrentState().getValue(),
                        Event.createAddEvent(this)
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
