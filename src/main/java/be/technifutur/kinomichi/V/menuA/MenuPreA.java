package be.technifutur.kinomichi.V.menuA;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuPreA extends Menu implements HasMenuItems {
    public MenuPreA() {
        super(States.PRE_MAIN_MENU, null,
                "Oui",
                "Non (défaut)"
        );
    }

    @Override
    protected String getMenuFooter() {
        return "";
    }
}
