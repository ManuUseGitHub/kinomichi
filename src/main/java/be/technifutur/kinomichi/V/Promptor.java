package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichi.V.menuA.MenuA;
import be.technifutur.kinomichi.V.menuA.MenuPreA;
import be.technifutur.kinomichi.V.menuB.*;
import be.technifutur.kinomichi.Version;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import store.luniversdemm.common.Saisir;
import store.luniversdemm.common.Utils;

import java.util.ArrayList;
import java.util.List;

public class Promptor {
    private static StateEngine engine;

    public static void setStateEngine(StateEngine engine){
        Promptor.engine = engine;
    }

    public static void getTitle(){
        System.out.printf(ConsoleColors.CYAN+"""
                 |  / _)                          _)        |     _)
                 ' /   |  __ \\    _ \\   __ `__ \\   |   __|  __ \\   |
                 . \\   |  |   |  (   |  |   |   |  |  (     | | |  |
                _|\\_\\ _| _|  _| \\___/  _|  _|  _| _| \\___| _| |_| _|
                V%s %s
                - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                """,ConsoleColors.RESET, Version.VERSION);
    }

    public static void clearAndGetTitle(){
        getTitle();
    }

    public static void getMenu(){
        System.out.println(switch (engine.getCurrentState()){
            // A
            case MAIN_MENU -> new MenuA();
            case PRE_MAIN_MENU -> new MenuPreA();

            // B
            case PLAGE_MANAGEMENT -> new MenuB();
            case PLAGE_ADDING -> new MenuB1();
            case PLAGE_DELETING -> new MenuB2();
            case PLAGE_EDIT -> new MenuB3();
            case PLAGE_LOADING -> new MenuB4();
            case PLAGE_LOADING_A -> new MenuB41();
            case PLAGE_LOADING_B -> new MenuB42();
            case PLAGE_SAVING -> new MenuB5();
            case PLAGE_LISTING -> new MenuB6();

            // C
            case PEOPLE_MANAGEMENT -> new MenuC();
            case PEOPLE_ADDING -> null;
            case PEOPLE_DELETING -> null;
            case PEOPLE_EDIT -> null;
            case PEOPLE_LISTING -> null;

            // D
            case ADMIN_MANAGEMENT -> new MenuD();
        });
    }

    public static void askWhatDo(){
        System.out.println("Que faire ?");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayModifyingTimeTable(TimeTable selected) {
        System.out.println("\n- - SÉLECTIONNÉ - -");
        System.out.println(selected);

        System.out.println("\nQuelle information voulez - vous changer ?");
        System.out.println("""
                            Activité    [1]     Qui anime       [3]
                            Description [2]     la temporalité  [4]
                                               (date + début + durée)
                            """);
        System.out.print("\n>");
    }

    public static List<String> selectTimeTables(TimeTables tts, String intro){
        Promptor.getMenu();

        List<Integer> list = new ArrayList<>();
        tts.getTimeTables().stream().peek(e -> list.add(e.getId())).forEach(System.out::println);
        System.out.println(intro);

        list.forEach(i -> System.out.print(" [" + ConsoleColors.BLUE + i + ConsoleColors.RESET + "]"));

        System.out.print("\n>");
        String ids = Saisir.scanString();

        List<String> arr = new ArrayList<>();
        Utils.onMatches("(\\d+)", ids, m -> {
            arr.add(m.group(1));
        });
        return arr;
    }
}
