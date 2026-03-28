package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class MenuA extends Menu implements HasMenuItems {
    private Stream<String> items;

    public MenuA() {
        this.name = "Menu Principal";
        this.items = Arrays.stream(new String[]{
                "Gestion des plages",
                "Gestion des participants",
                "Administration des données"
        });
    }

    @Override
    protected String getMenuFooter() {
        return """
                - - - - - QUITTER - - - - -
                [q] quitter / annuler
                - - - - - - - - - - - - - -
                """;
    };

    @Override
    public Stream<String> getItems() {
        return items;
    }

    @Override
    protected String getMenuBody() {
        AtomicInteger start = new AtomicInteger(1);
        return """
                %s
                """.formatted(getItems()
                .map(s -> ("[%d] %s".formatted(start.getAndAdd(1),s))
                ).collect(Collectors.joining("\n")));
    }
}
