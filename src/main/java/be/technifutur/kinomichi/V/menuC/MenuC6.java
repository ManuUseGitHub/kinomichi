package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC6 extends Menu implements HasMenuItems {
    public MenuC6() {
        super(States.PEOPLE_LISTING,States.PEOPLE_MANAGEMENT);
    }

    @Override
    protected String getMenuFooter() {

        return """
                Prenez le temps de vérifier vos plages
                """;
    }
}
