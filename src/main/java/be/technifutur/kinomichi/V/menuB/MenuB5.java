package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB5 extends Menu implements HasMenuItems {
    public MenuB5() {
        super(States.PLAGE_SAVING,States.PLAGE_MANAGEMENT);
    }
    @Override
    public String getMenuFooter(){
        return """
                ... Sélectionnez un nom de fichier de sauvegarde ...
                """;
    }
}
