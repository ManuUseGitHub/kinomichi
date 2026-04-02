package be.technifutur.kinomichi;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;

public class StateEngineHelper {
    public static void navigateFromHome(){
        StateEngine
                .getInstance()
                .initStateEngine(States.MAIN_MENU);

        StateEngine
                .getInstance()
                .apply(Constants.GO_HOME_CODE_GRANTED);
    }
}
