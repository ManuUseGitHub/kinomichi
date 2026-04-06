package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;

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
        States nextState = this.dispatchStatesDecision(event);
        currentState = nextState;
    }


    private States dispatchStatesDecision(long event){
        Menu menu = Promptor.getMenu();
        States nextState = menu.getNextState(event);
        return event == Constants.EXIT_CODE && currentState == States.MAIN_MENU ? null :
                nextState != null ? nextState :
                currentState;
    }

    public States getCurrentState() {
        return currentState;
    }
}
