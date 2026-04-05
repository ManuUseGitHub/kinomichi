package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC41 extends Menu implements HasMenuItems {
    public MenuC41() {
        super(States.PLAGE_LOADING_A.getLabel(), Arrays.stream(new String[]{}));
    }

    @Override
    public String getMenuFooter(){
        return """
                Sélectionnez une sauvegarde
                """;
    }
}
