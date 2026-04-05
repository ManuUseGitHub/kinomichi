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

import java.util.concurrent.atomic.AtomicInteger;

public class Kinomichi implements Savable {

    private final StateEngine stateEngine;
    private final RequestTranslator requestTranslator;
    private boolean saved = true;
    private boolean lock;

    public Kinomichi() {
        this.requestTranslator = new RequestTranslator();
        stateEngine = StateEngine.getInstance();
        stateEngine.initStateEngine(States.MAIN_MENU);

        Promptor.setStateEngine(this.stateEngine);
    }

    public void run() {
        AtomicInteger data = new AtomicInteger(0);
        TimeTables tts = new TimeTables();
        new StarterMS(tts, States.MAIN_MENU.getValue());
        new AddPlageMS(tts, States.PLAGE_ADDING.getValue());
        new DeletePlageMS(tts, States.PLAGE_DELETING.getValue());
        new ListingPlageMS(tts, States.PLAGE_LISTING.getValue());
        new SavePlageMS(tts, States.PLAGE_SAVING.getValue());
        new EditPlageMS(tts, States.PLAGE_EDIT.getValue());
        new LoadPlageMS(tts,
                States.PLAGE_LOADING_A.getValue(),
                States.PLAGE_LOADING_B.getValue());

        String current = null;

        EventBus.registerListener(Event.Topic.LOCK.name(), new IEventListener() {
            @Override
            public void processEvent(Event event) {
                if (event.eventType() == Event.EventType.UNLOCK) {
                    lock = false;
                    stateEngine.apply(Constants.BACK_CODE);
                } else if (event.eventType() == Event.EventType.LOCK) {
                    lock = true;
                }
            }
        });
        EventBus.publishEvent(
                States.MAIN_MENU.getValue(),
                Event.createNavEvent(this)
        );
        while (stateEngine.getCurrentState() != null) {
            try {
                current = decisionLoopLogic(current,data);
                current = handleViewChanged(current);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String decisionLoopLogic(String current, AtomicInteger data) throws InterruptedException {
        if (!this.lock) {
            // Only print AFTER state change
            Promptor.getMenu();
            Promptor.askWhatDo();
            data.set((int)this.requestTranslator.translate(Saisir.scanString(), this));
            current = stateEngine.getCurrentState().getValue();

            stateEngine.apply(data.get());
        } else {
            Thread.sleep(1000);
        }
        return current;
    }

    private String handleViewChanged(String current) throws InterruptedException {
        if (stateEngine.getCurrentState() != null && !current.equals(stateEngine.getCurrentState().getValue())) {
            current = stateEngine.getCurrentState().getValue();
            lockOnActivityViews(this);
            EventBus.publishEvent(
                    stateEngine.getCurrentState().getValue(),
                    Event.createNavEvent(this)
            );
        }
        return current;
    }

    private void lockOnActivityViews(Kinomichi kinomichi) throws InterruptedException {
        if (stateEngine.getCurrentState().getValue().matches("b1|b2|b3|b5|b41|b42"+"|"+"c1|c2|c3|c5|c41|c42")) {
            EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createLockEvent(kinomichi));
            Thread.sleep(500);
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
