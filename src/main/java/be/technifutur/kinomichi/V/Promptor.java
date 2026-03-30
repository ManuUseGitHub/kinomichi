package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.V.menuA.MenuA;
import be.technifutur.kinomichi.V.menuB.*;
import be.technifutur.kinomichi.Version;

public class Promptor {
    private static StateEngine engine;

    public static void setStateEngine(StateEngine engine){
        Promptor.engine = engine;
    }

    public static void getTitle(){
        System.out.printf("""
                 |  / _)                          _)        |     _)
                 ' /   |  __ \\    _ \\   __ `__ \\   |   __|  __ \\   |
                 . \\   |  |   |  (   |  |   |   |  |  (     | | |  |
                _|\\_\\ _| _|  _| \\___/  _|  _|  _| _| \\___| _| |_| _|
                V %s
                - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
                """, Version.VERSION);
    }

    public static void clearAndGetTitle(){
        getTitle();
    }

    public static void getMenu(){
        System.out.println(switch (engine.getCurrentState()){
            case MAIN_MENU -> new MenuA();

            // B
            case PLAGE_MANAGEMENT -> new MenuB();
            case PLAGE_ADDING -> new MenuB1();
            case PLAGE_DELETING -> new MenuB2();
            case PLAGE_EDIT -> new MenuB3();
            case PLAGE_LISTING -> new MenuB4();

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
}
