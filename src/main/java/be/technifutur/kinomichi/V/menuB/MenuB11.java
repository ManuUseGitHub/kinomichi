package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB11 extends Menu implements HasMenuItems {
    public MenuB11() {
        super("[B] Ajout de plages", Arrays.stream(new String[]{}));
    }

    @Override
    protected String getMenuFooter() {
        return "Entrez les informations d'une nouvelle plage";
    }
}
