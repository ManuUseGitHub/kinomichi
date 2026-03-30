package be.technifutur.kinomichi.V.menuA;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuA extends Menu implements HasMenuItems {
    public MenuA() {
        super("Menu principal", Arrays.stream(new String[]{
                "Gestion des plages",
                "Gestion des participants",
                "Administration des données"
        }));
    }

    @Override
    protected String getMenuFooter() {
        return """
                - - - - - QUITTER - - - - -
                [q] quitter
                - - - - - - - - - - - - - -
                """;
    }
}
