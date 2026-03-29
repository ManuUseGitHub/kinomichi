package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB1 extends Menu implements HasMenuItems {
    public MenuB1() {
        super("[B] Ajout de plages", Arrays.stream(new String[]{
                "Ajouter",
                "Modification les plages",
                "Aperçu des plages",
                "Sauvegarder"
        }));
    }
}
