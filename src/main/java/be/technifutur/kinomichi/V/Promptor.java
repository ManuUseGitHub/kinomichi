package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.Participant;
import be.technifutur.kinomichi.M.Participants;
import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.V.menuA.*;
import be.technifutur.kinomichi.V.menuB.*;
import be.technifutur.kinomichi.V.menuC.*;
import be.technifutur.kinomichi.Version;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.V.ConsoleColors;
import be.technifutur.kinomichicommon.interfaces.GroupManaging;
import be.technifutur.kinomichicommon.interfaces.HasId;
import store.luniversdemm.common.Saisir;
import store.luniversdemm.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static store.luniversdemm.common.Utils.evenListOf;

public class Promptor {
    private static StateEngine engine;
    private static Menu currentMenu;
    private static HashMap<States, Menu> tempMenus = new HashMap<>();

    public static void setStateEngine(StateEngine engine) {
        Promptor.engine = engine;
    }

    public static void getTitle() {
        System.out.printf(ConsoleColors.CYAN + """
                 |  / _)                          _)        |     _)
                 ' /   |  __ \\    _ \\   __ `__ \\   |   __|  __ \\   |
                 . \\   |  |   |  (   |  |   |   |  |  (     | | |  |
                _|\\_\\ _| _|  _| \\___/  _|  _|  _| _| \\___| _| |_| _|
                V%s %s
                - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                """, ConsoleColors.RESET, Version.VERSION);
    }

    public static void clearAndGetTitle() {
        getTitle();
    }

    public static void setCurrentMenu() {
        States currentState = engine.getCurrentState() == null ?
                States.MAIN_MENU :
                engine.getCurrentState();

        if (!tempMenus.containsKey(currentState)) {
            tempMenus.put(currentState, stateToMenu(currentState));
        }
    }

    public static Menu stateToMenu(States state) {
        return switch (state) {
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
            case PEOPLE_ADDING -> new MenuC1();
            case PEOPLE_DELETING -> new MenuC2();
            case PEOPLE_EDIT -> new MenuC3();
            case PEOPLE_LOADING -> new MenuC4();
            case PEOPLE_LOADING_A -> new MenuC41();
            case PEOPLE_LOADING_B -> new MenuC42();
            case PEOPLE_SAVING -> new MenuC5();
            case PEOPLE_LISTING -> new MenuC6();

            // D
            case ADMIN_MANAGEMENT -> new MenuD();
        };
    }

    public static Menu getMenu() {
        setCurrentMenu();
        return tempMenus.get(engine.getCurrentState());
    }

    public static void displayMenu() {
        setCurrentMenu();
        System.out.println(tempMenus.get(engine.getCurrentState()));
    }

    public static void askWhatDo() {
        System.out.println("Que faire ?");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayModifyingItem(TimeTable selected) {
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

    public static void displayModifyingItem(Participant selected) {
        System.out.println("\n- - SÉLECTIONNÉ - -");
        System.out.println(selected);

        System.out.println("\nQuelle information voulez - vous changer ?");
        System.out.println("""
                Type de personne        [1]
                nom     [2]     prenom  [3]
                email   [4]  telephone  [5]
                club    [6]      grade  [7]
                """);
        System.out.print("\n>");
    }

    public static <T extends HasId, B>List<String> selectItems(GroupManaging<T,B> tts, String intro) {
        Promptor.displayMenu();

        List<Integer> list = new ArrayList<>();
        tts.getItems().stream().peek(e -> list.add(e.getId())).forEach(System.out::println);
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

    public static void displayParticipantsTypesProposition(){
        AtomicInteger cpt = new AtomicInteger(1);
        AtomicInteger maxSize = new AtomicInteger(0);

        ParticipantType[] arr = evenListOf(ParticipantType.class,maxSize);

        maxSize.set(maxSize.get()+1);

        Arrays.stream(arr).forEach(p -> {
            if(p != null){
                int repeatX = maxSize.get() - p.getName().length();
                System.out.print("[%s] %s".formatted(ConsoleColors.BLUE+cpt.get()+ConsoleColors.RESET,
                        p.getName()+".".repeat(repeatX)+"("+ConsoleColors.CYAN+p.getCode())+ConsoleColors.RESET+").");
            }else{
                System.out.print("..........."+".".repeat(maxSize.get()));
            }

            if(cpt.get()%2 == 0){
                System.out.println();
            }
            cpt.set(cpt.get()+1);
        });
    }

    public static void displayStatesTypesProposition(){
        AtomicInteger cpt = new AtomicInteger(0);
        AtomicInteger maxSize = new AtomicInteger(0);
        States[] arr = evenListOf(States.class,maxSize);


        Arrays.stream(arr).forEach(p -> {
            if(p != null){
                int repeatX = maxSize.get() - p.getName().length();
                System.out.print("[%s] %s".formatted(ConsoleColors.BLUE+cpt.get()+ConsoleColors.RESET,
                        p.getName()+".".repeat(repeatX)+"("+ConsoleColors.CYAN)+ConsoleColors.RESET+").");
            }else{
                System.out.print("..........."+".".repeat(maxSize.get()));
            }

            if(cpt.get()%2 == 0){
                System.out.println();
            }
            cpt.set(cpt.get()+1);
        });
    }
}
