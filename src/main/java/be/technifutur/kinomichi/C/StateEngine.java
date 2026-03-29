package be.technifutur.kinomichi.C;

import be.technifutur.kinomichicommon.C.States;

public class StateEngine {

    private States currentState;

    public StateEngine(States state) {
        this.currentState = state;
    }

    public States getCurrentState() {
        return currentState;
    }

    public void apply(long i) {
        this.currentState = switch((int)i){
            case 1 -> States.PLAGE_MANAGEMENT;
            case 2 -> States.PEOPLE_MANAGEMENT;
            case 3 -> States.ADMIN_MANAGEMENT;
            case -996 -> States.MAIN_MENU;
            default -> currentState;
        };
    }
}
