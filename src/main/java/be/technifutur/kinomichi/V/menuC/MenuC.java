package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public class MenuC extends Menu implements HasMenuItems {
    public MenuC() {
        super(States.PEOPLE_MANAGEMENT,States.MAIN_MENU,
                "Ajout de participants",
                "Suppression de participants",
                "Modification d'un participant",
                "Charger des participants",
                "Sauvegarder les participants",
                "Listing des participants"
        );
    }

    @Override
    public States getNextState(long event){
        return switch((int)event){
            case 1 -> States.PEOPLE_ADDING;
            case 2 -> States.PEOPLE_DELETING;
            case 3 -> States.PEOPLE_EDIT;
            case 4 -> States.PEOPLE_LOADING;
            case 5 -> States.PEOPLE_SAVING;
            case 6 -> States.PEOPLE_LISTING;
            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> previousView;
            default -> state;
        };
    }
}
