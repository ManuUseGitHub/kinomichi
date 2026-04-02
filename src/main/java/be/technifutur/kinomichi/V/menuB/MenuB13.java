package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB13 extends Menu implements HasMenuItems {
    public MenuB13() {
        super("[B] Listing des plages", Arrays.stream(new String[]{}));
    }

    @Override
    protected String getMenuFooter() {

        return """
                Prenez le temps de vérifier vos plages
                
                - - - - - REVENIR - - - - -
                [r] retour au menu précédent
                - - - - - - - - - - - - - -
                """;
    }


}
