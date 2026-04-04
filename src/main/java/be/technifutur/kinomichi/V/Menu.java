package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;
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
        return "- - %s - -".formatted(

                name);
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
                .map(s -> ("[%d] %s".formatted(start.getAndAdd(1), s))
                ).collect(Collectors.joining("\n")));
    }

    protected String getMenuFooter() {
        return """
                - - - - - REVENIR - - - - -
                [%s] Revenir en arrière
                [%s] Menu principal
                - - - - - - - - - - - - - -
                """.formatted("r","a");
    }

    ;

    @Override
    public String toString() {
        String[] lines = "%s\n%s%s".formatted(
                getMenuHeader(),
                getMenuBody(),
                getMenuFooter()
        ).split("\n");

        int maxLength = 0;

        for (String str : lines) {
            if (str != null && str.length() > maxLength) {
                maxLength = str.length();
            }
        }

        StringBuilder sb = new StringBuilder();

        String border = ConsoleColors.BLACK+ConsoleColors.CYAN_BACKGROUND+"─".repeat(maxLength)+ConsoleColors.RESET;

        sb.append("┌").append(border).append("┐\n");

        for (String l : lines) {
            int padding = maxLength - l.length();
            String padded = l + " ".repeat(padding);
            sb
                    .append(ConsoleColors.BLACK+ConsoleColors.CYAN_BACKGROUND)
                    .append("│")
                    .append(ConsoleColors.BLACK+ ConsoleColors.CYAN_BACKGROUND+padded)
                    .append(ConsoleColors.BLACK+ConsoleColors.CYAN_BACKGROUND+"│")
                    .append(ConsoleColors.RESET)
                    .append("\n");
        }

        sb.append("└").append(border).append("┘\n");

        return sb.toString();
    }
}
