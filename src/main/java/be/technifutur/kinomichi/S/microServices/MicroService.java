package be.technifutur.kinomichi.S.microServices;

import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

public abstract class MicroService implements MicroServiable {
    public MicroService(String event){
        EventBus.registerListener(event, handle());
    }

    public MicroService(String ... events){
        for (String event : events) {
            EventBus.registerListener(event, handle());
        }

    }
}
