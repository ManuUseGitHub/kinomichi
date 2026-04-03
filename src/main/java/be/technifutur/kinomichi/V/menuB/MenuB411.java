package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB411 extends Menu implements HasMenuItems {
    public MenuB411() {
        super("Chargement par objets", Arrays.stream(new String[]{}));
    }
    @Override
    public String getMenuFooter(){
        return """
                ... Sélectionnez un fichier à charger ...
                """;
    }
}

