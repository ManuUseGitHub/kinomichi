package be.technifutur.kinomichi.S;

import be.technifutur.kinomichicommon.C.Event;
import be.technifutur.kinomichicommon.C.EventBus;
import be.technifutur.kinomichicommon.interfaces.IEventListener;
import be.technifutur.kinomichicommon.interfaces.MicroServiable;

public class NavigatorUtils {
    public static void getBackOnSuccess(IEventListener sender){
        getBackAndUnlock(sender);
    }

    public static void getBackOnSuccess(MicroServiable sender){
        getBackAndUnlock(sender);
    }

    private static void getBackAndUnlock(Object sender){
        EventBus.publishEvent(Event.Topic.NAVIGATION.name(), Event.createBackNavEvent(sender));
        EventBus.publishEvent(Event.Topic.LOCK.name(), Event.createUnlockEvent(sender));
    }
}
