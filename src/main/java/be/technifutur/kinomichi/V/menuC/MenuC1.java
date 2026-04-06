package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC1 extends Menu implements HasMenuItems {
    public MenuC1() {
        super(States.PEOPLE_ADDING,States.PEOPLE_MANAGEMENT);
    }

    @Override
    protected String getMenuFooter() {
        return "Entrez les informations d'un nouveau participant";
    }
}
