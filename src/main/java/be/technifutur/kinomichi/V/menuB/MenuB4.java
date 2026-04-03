package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB4 extends Menu implements HasMenuItems {
    public MenuB4() {
        super("Chargement de plages", Arrays.stream(new String[]{
                "Par objects",
                "Par textes générés"
        }));
    }
}

