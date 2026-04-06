package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB4 extends Menu implements HasMenuItems {
    public MenuB4() {
        super(States.PLAGE_LOADING,States.PLAGE_MANAGEMENT,
                "Par sauvegarde",
                "Par textes générés"
        );
    }

    @Override
    public States getNextState(long event) {
        return switch((int)event){
            case 1 -> States.PLAGE_LOADING_A;
            case 2 -> States.PLAGE_LOADING_B;
            case Constants.BACK_CODE -> previousView;
            case Constants.GO_HOME_CODE_CONDITIONED -> States.PRE_MAIN_MENU;
            case Constants.GO_HOME_CODE_GRANTED -> States.MAIN_MENU;
            default -> null;
        };
    }
}

