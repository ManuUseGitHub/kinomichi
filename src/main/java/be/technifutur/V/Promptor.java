package be.technifutur.V;

import be.technifutur.Constants;

public class Promptor {
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

    public static void getMenuA(){
        System.out.println(new MenuA().toString());
    }

    public static void askWhatDo(){
        System.out.println("Que faire ?");
    }

    public static void clearWithANSICodes() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
