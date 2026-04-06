package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC3 extends Menu implements HasMenuItems {
    public MenuC3() {
        super(States.PEOPLE_EDIT,States.PEOPLE_MANAGEMENT);
    }
}
