package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB2 extends Menu implements HasMenuItems {
    public MenuB2() {
        super(States.PLAGE_DELETING.getLabel(), Arrays.stream(new String[]{}));
    }
}
