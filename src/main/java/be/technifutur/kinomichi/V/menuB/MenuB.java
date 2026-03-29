package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB extends Menu implements HasMenuItems {
    public MenuB() {
        super("Gestion des plages", Arrays.stream(new String[]{
                "Ajout de plages",
                "Suppression de plages",
                "Modification d'une plage",
                "Listing des plages"
        }));
    }
}
