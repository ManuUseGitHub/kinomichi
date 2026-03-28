package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;
import java.util.stream.Stream;

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
