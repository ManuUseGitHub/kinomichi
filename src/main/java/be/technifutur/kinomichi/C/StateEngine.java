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

    private States plagesDecisionB(long event){

        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LOADING;
            case 5 -> States.PLAGE_SAVING;
            case 6 -> States.PLAGE_LISTING;

            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    private States plagesDecisionActivity(long event){
        return switch((int)event){
            case Constants.BACK_CODE -> States.PLAGE_MANAGEMENT;
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
            case PLAGE_MANAGEMENT ->  plagesDecisionB(event);
            case PLAGE_LOADING -> plageDecisionB4(event);
            case PLAGE_ADDING,
                 PLAGE_DELETING,
                 PLAGE_EDIT,
                 PLAGE_LOADING_A ,
                 PLAGE_LOADING_B,
                 PLAGE_SAVING,
                 PLAGE_LISTING-> plagesDecisionActivity(event);

            // C
            case PEOPLE_MANAGEMENT -> peopleDecision(event);

            // D
            case ADMIN_MANAGEMENT -> adminDecision(event);
            default -> homeDecision(event);
        };
    }

    private States plageDecisionB4(long event) {
        return switch((int)event){
            case 1 -> States.PLAGE_LOADING_A;
            case 2 -> States.PLAGE_LOADING_B;
            case Constants.BACK_CODE -> States.PLAGE_MANAGEMENT;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> currentState;
        };
    }

    public States getCurrentState() {
        return currentState;
    }
}
