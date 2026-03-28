package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.Constants;

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
                """, Constants.VERSION);
    }

    public static void clearAndGetTitle(){
        getTitle();
    }

    public static void getMenu(){
        if("a".equals(engine.getCurrentState().getValue())){
            System.out.println(new MenuA());
        }else if("b".equals(engine.getCurrentState().getValue())){
            System.out.println(new MenuB());
        }
    }

    public static void askWhatDo(){
        System.out.println("Que faire ?");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
