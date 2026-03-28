package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.interfaces.HasMenuItems;

public abstract class Menu implements HasMenuItems {
    protected String name;

    protected String getMenuHeader() {
        return "- - MENU %s - -".formatted(name);
    }
    protected abstract String getMenuBody();
    protected String getMenuFooter() {
        return """
                - - - - - QUITTER - - - - -
                [r] quitter / annuler
                - - - - - - - - - - - - - -
                """;
    };

    @Override
    public String toString() {
        return "%s\n%s\n%s".formatted(getMenuHeader(), getMenuBody(), getMenuFooter());
    }
}
