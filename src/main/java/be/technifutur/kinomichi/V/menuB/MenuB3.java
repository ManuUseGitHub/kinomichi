package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB3 extends Menu implements HasMenuItems {
    public MenuB3() {
        super("[B] Modifier des plages", Arrays.stream(new String[]{
                "Choisir des plages et modifier",
                "Aperçu des plages à modifier",
                "Sauvegarder"
        }));
    }
}
