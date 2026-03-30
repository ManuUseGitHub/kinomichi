package be.technifutur.kinomichi.C;

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
        currentState = this.dispatchStatesDecision(event);
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
            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States plagesDecisionB1(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LISTING;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States plagesDecisionB2(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LISTING;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States plagesDecisionB3(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LISTING;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States plagesDecisionB4(long event){
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LISTING;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States peopleDecision(long event){
        return switch((int)event){
            case 1 -> States.PEOPLE_ADDING;
            case 2 -> States.PEOPLE_DELETING;
            case 3 -> States.PEOPLE_EDIT;
            case 4 -> States.PEOPLE_LISTING;
            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States adminDecision(long event){
        return switch((int)event){
            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States dispatchStatesDecision(long event){
        return switch (currentState){
            case MAIN_MENU ->  homeDecision(event);

            // B
            case PLAGE_MANAGEMENT ->  plagesDecision(event);
            case PLAGE_ADDING ->  plagesDecisionB1(event);
            case PLAGE_DELETING ->  plagesDecisionB2(event);
            case PLAGE_EDIT ->  plagesDecisionB3(event);
            case PLAGE_LISTING ->  plagesDecisionB4(event);

            // C
            case PEOPLE_MANAGEMENT -> peopleDecision(event);

            // D
            case ADMIN_MANAGEMENT -> adminDecision(event);
            default -> homeDecision(event);
        };
    }

    public States getCurrentState() {
        return currentState;
    }
}
