package be.technifutur.kinomichi.V.menuA;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuA extends Menu implements HasMenuItems {
    public MenuA() {
        super(States.MAIN_MENU,null,"Gestion des plages",
                "Gestion des participants",
                "Administration des données"
        );
    }

    @Override
    protected String getMenuFooter() {
        return """
                - - - - - QUITTER - - - - -
                [q] quitter
                - - - - - - - - - - - - - -
                """;
    }

    @Override
    public States getNextState(long event) {
        return switch((int)event){
            case 1 -> States.PLAGE_MANAGEMENT;
            case 2 -> States.PEOPLE_MANAGEMENT;
            case 3 -> States.ADMIN_MANAGEMENT;
            case -999 -> null;
            default -> state;
        };
    }
}
