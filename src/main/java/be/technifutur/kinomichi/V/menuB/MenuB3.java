package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB3 extends Menu implements HasMenuItems {
    public MenuB3() {
        super(States.PLAGE_EDIT.getLabel(), Arrays.stream(new String[]{}));
    }
}
