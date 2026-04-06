package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC5 extends Menu implements HasMenuItems {
    public MenuC5() {
        super(States.PEOPLE_SAVING,States.PEOPLE_MANAGEMENT);
    }
    @Override
    public String getMenuFooter(){
        return """
                ... Sélectionnez un nom de fichier de sauvegarde ...
                """;
    }
}
