package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC4 extends Menu implements HasMenuItems {
    public MenuC4() {
        super(States.PEOPLE_LOADING,States.PEOPLE_MANAGEMENT,
                "Par sauvegarde",
                "Par textes générés"
        );
    }

    @Override
    public States getNextState(long event) {
        return switch((int)event){
            case 1 -> States.PEOPLE_LOADING_A;
            case 2 -> States.PEOPLE_LOADING_B;
            case Constants.BACK_CODE -> previousView;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> null;
        };
    }
}

