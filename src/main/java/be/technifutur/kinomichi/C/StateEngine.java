package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import store.luniversdemm.common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static be.technifutur.kinomichi.V.Promptor.stateToMenu;
import static store.luniversdemm.common.Utils.hasListDuplicates;

public class StateEngine {

    private States currentState;

    //region Singleton part
    private StateEngine(){};
    private static StateEngine INSTANCE;
    public static StateEngine getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StateEngine();
        }

        return INSTANCE;
    }
    //endregion

    public void initStateEngine(States initialState){
        if (currentState == null){
            currentState = initialState;
        }
    }

    public void apply(long event) {
        List<States> history = new ArrayList<>();
        AtomicReference<States> tempState = new AtomicReference<>(currentState);
        Utils.onMatches("(-\\d+|\\d)",String.valueOf(event),m -> {
            tempState.set(this.dispatchStatesDecision(tempState.get(), Long.parseLong(m.group(1))));
            history.add(tempState.get());
        });

        if(!hasListDuplicates(history)){
            currentState = history.getLast();
        }
    }

    private States dispatchStatesDecision(States currentState , long event){
        Menu menu = stateToMenu(currentState);
        States nextState = menu.getNextState(event);
        return event == Constants.EXIT_CODE && currentState == States.MAIN_MENU ? null :
                nextState != null ? nextState :
                currentState;
    }

    public States getCurrentState() {
        return currentState;
    }
}
