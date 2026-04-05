package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC4 extends Menu implements HasMenuItems {
    public MenuC4() {
        super(States.PEOPLE_LOADING.getLabel(), Arrays.stream(new String[]{
                "Par sauvegarde",
                "Par textes générés"
        }));
    }
}

