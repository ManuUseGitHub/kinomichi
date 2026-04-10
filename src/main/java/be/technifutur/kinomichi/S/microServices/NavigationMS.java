package be.technifutur.kinomichi.S.microServices;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

public class NavigationMS extends MicroService implements MicroServiable {
    public NavigationMS(String event) {
        super(event);
    }


    @Override
    public IEventListener handle() {
        return event -> {
            if (event.eventType() == Event.EventType.BACK_NAVIGATE) {
                StateEngine.getInstance().apply(Constants.BACK_CODE);
            }
        };
    }
}
