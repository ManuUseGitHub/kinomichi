package be.technifutur;

import be.technifutur.C.Kinomichi;
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

        try{
            Saisir.openScanner();
            Kinomichi prog = new Kinomichi();
            String choix = "1";

            prog.request(1);
        }finally {
            Saisir.closeScanner();
        }
    }
}
