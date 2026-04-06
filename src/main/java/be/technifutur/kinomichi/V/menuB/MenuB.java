package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB extends Menu implements HasMenuItems {
    public MenuB() {
        super(States.PLAGE_MANAGEMENT,States.MAIN_MENU,
                "Ajout de plages",
                "Suppression de plages",
                "Modification d'une plage",
                "Charger des plages",
                "Sauvegarder les plages",
                "Listing des plages"
        );
    }

    @Override
    public States getNextState(long event) {
        return switch((int)event){
            case 1 -> States.PLAGE_ADDING;
            case 2 -> States.PLAGE_DELETING;
            case 3 -> States.PLAGE_EDIT;
            case 4 -> States.PLAGE_LOADING;
            case 5 -> States.PLAGE_SAVING;
            case 6 -> States.PLAGE_LISTING;

            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> previousView;
            default -> state;
        };
    }
}
