package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Menu implements HasMenuItems {
    private final String name;
    private final Stream<String> items;

    protected Menu(String name, Stream<String> items) {
        this.name = name;
        this.items = items;
    }

    protected String getMenuHeader() {
        return "- - %s - -".formatted(name);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public Stream<String> getItems() {
        return items;
    }

    protected String getMenuBody() {
        AtomicInteger start = new AtomicInteger(1);
        return """
                %s
                """.formatted(getItems()
                .map(s -> ("[%d] %s".formatted(start.getAndAdd(1),s))
                ).collect(Collectors.joining("\n")));
    }

    protected String getMenuFooter() {
        return """
                - - - - - REVENIR - - - - -
                [r] Revenir en arrière
                - - - - - - - - - - - - - -
                """;
    };

    @Override
    public String toString() {
        return "%s\n%s\n%s".formatted(getMenuHeader(), getMenuBody(), getMenuFooter());
    }
}
