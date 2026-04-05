package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB1 extends Menu implements HasMenuItems {
    public MenuB1() {
        super(States.PLAGE_ADDING.getLabel(), Arrays.stream(new String[]{}));
    }

    @Override
    protected String getMenuFooter() {
        return "Entrez les informations d'une nouvelle plage";
    }
}
