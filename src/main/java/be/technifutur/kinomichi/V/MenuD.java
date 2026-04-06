package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuD extends Menu implements HasMenuItems {
    public MenuD() {
        super(States.ADMIN_MANAGEMENT, States.MAIN_MENU);
    }

    @Override
    public States getNextState(long event) {
        return switch((int)event){
            case Constants.GO_HOME_CODE_GRANTED,
                 Constants.BACK_CODE -> previousView;
            default -> state;
        };
    }
}
