package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB extends Menu implements HasMenuItems {
    public MenuB() {
        super(States.PLAGE_MANAGEMENT.getLabel(), Arrays.stream(new String[]{
                "Ajout de plages",
                "Suppression de plages",
                "Modification d'une plage",
                "Charger des plages",
                "Sauvegarder les plages",
                "Listing des plages"
        }));
    }
}
