package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC2 extends Menu implements HasMenuItems {
    public MenuC2() {
        super(States.PEOPLE_DELETING.getLabel(), Arrays.stream(new String[]{}));
    }
}
