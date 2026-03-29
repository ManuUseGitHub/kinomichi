package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB2 extends Menu implements HasMenuItems {
    public MenuB2() {
        super("[B] Suppression de plages", Arrays.stream(new String[]{
                "Choisir et supprimer",
                "Aperçu des plages à supprimer",
                "Sauvegarder"
        }));
    }
}
