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

    public void apply(long event) {
        this.currentState = this.dispatchStatesDecision(event);
    }

    private States homeDecision(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_MANAGEMENT;
            case 2 -> States.PEOPLE_MANAGEMENT;
            case 3 -> States.ADMIN_MANAGEMENT;
            case -999 -> null;
            default -> currentState;
        };
    }

    private States plagesDecision(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LISTING;
            case -996 -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States peopleDecision(long event){
        return switch((int)event){
            case 1 -> States.PEOPLE_ADDING;
            case 2 -> States.PEOPLE_DELETING;
            case 3 -> States.PEOPLE_EDIT;
            case 4 -> States.PEOPLE_LISTING;
            case -996 -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States adminDecision(long event){
        return switch((int)event){
            case -996 -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States dispatchStatesDecision(long event){
        return switch (currentState){
            case MAIN_MENU ->  homeDecision(event);
            case PLAGE_MANAGEMENT ->  plagesDecision(event);
            case PEOPLE_MANAGEMENT -> peopleDecision(event);
            case ADMIN_MANAGEMENT -> adminDecision(event);
            default -> homeDecision(event);
        };
    }
}
