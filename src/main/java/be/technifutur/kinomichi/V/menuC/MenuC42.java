package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC42 extends Menu implements HasMenuItems {
    public MenuC42() {
        super(States.PEOPLE_LOADING_B,States.PEOPLE_MANAGEMENT);
    }

    @Override
    public String getMenuFooter() {
        return """
                Sélectionnez un fichier contenant l'affichage d'une liste
                """;
    }
}

