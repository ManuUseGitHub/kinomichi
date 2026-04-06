package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB42 extends Menu implements HasMenuItems {
    public MenuB42() {
        super(States.PLAGE_LOADING_B,States.PLAGE_MANAGEMENT);
    }

    @Override
    public String getMenuFooter() {
        return """
                Sélectionnez un fichier contenant l'affichage d'une liste
                """;
    }
}

