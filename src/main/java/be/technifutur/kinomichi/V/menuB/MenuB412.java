package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB412 extends Menu implements HasMenuItems {
    public MenuB412() {
        super("Chargement par log text", Arrays.stream(new String[]{}));
    }
    @Override
    public String getMenuFooter(){
        return """
                ... Sélectionnez un fichier à charger ...
                """;
    }
}

