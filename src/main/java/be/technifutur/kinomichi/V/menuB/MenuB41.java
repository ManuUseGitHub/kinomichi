package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB41 extends Menu implements HasMenuItems {
    public MenuB41() {
        super("(B41) Chargement d'une sauvegarde", Arrays.stream(new String[]{}));
    }

    @Override
    public String getMenuFooter(){
        return """
                Sélectionnez une sauvegarde
                """;
    }
}
