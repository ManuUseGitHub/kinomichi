package be.technifutur.kinomichi;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.States;

public class StateEngineHelper {
    public static void navigateFromHome(){
        StateEngine
                .getInstance()
                .initStateEngine(States.MAIN_MENU);

        StateEngine
                .getInstance()
                .apply(-996);


    }
}
