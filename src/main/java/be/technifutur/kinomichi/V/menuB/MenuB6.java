package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB6 extends Menu implements HasMenuItems {
    public MenuB6() {
        super("(B6) Listing des plages", Arrays.stream(new String[]{}));
    }

    @Override
    protected String getMenuFooter() {

        return """
                Prenez le temps de vérifier vos plages
                """;
    }
}
