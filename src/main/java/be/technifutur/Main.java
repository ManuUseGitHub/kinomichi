package be.technifutur;

import store.luniversdemm.common.Saisir;

public class Main {
    static void main(String... args){
        System.out.printf("""
                 ____  __.__                      .__       .__    .__ \s
                |    |/ _|__| ____   ____   _____ |__| ____ |  |__ |__|\s
                |      < |  |/    \\ /  _ \\ /     \\|  |/ ___\\|  |  \\|  |\s
                |    |  \\|  |   |  (  <_> )  Y Y  \\  \\  \\___|   Y  \\  |\s
                |____|__ \\__|___|  /\\____/|__|_|  /__|\\___  >___|  /__|\s
                        \\/       \\/             \\/        \\/     \\/    \s
                V %s \n
                """,Constants.VERSION);

        Saisir.openScanner();

        System.out.println("Type hello");
        String hello = Saisir.scanString();

        System.out.println("Type world");
        String world = Saisir.scanString();

        System.out.println("%s %s".formatted(hello,world));

        Saisir.closeScanner();
    }
}
