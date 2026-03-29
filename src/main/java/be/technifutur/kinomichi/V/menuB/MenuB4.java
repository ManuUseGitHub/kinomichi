package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB4 extends Menu implements HasMenuItems {
    public MenuB4() {
        super("[B] Listing des plages", Arrays.stream(new String[]{
                "Afficher par filtres",
                "Afficher toutes les plages"
        }));
    }
}
