package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB6 extends Menu implements HasMenuItems {
    public MenuB6() {
        super(States.PLAGE_LISTING,States.PLAGE_MANAGEMENT);
    }

    @Override
    protected String getMenuFooter() {

        return """
                Prenez le temps de vérifier vos plages
                """;
    }
}
