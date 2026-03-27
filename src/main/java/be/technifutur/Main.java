package be.technifutur;

import be.technifutur.C.Kinomichi;
import store.luniversdemm.common.Saisir;

public class Main {
    static void main(String... args){
        System.out.printf("""
                 |  / _)                          _)        |     _)
                 ' /   |  __ \\    _ \\   __ `__ \\   |   __|  __ \\   |
                 . \\   |  |   |  (   |  |   |   |  |  (     | | |  |
                _|\\_\\ _| _|  _| \\___/  _|  _|  _| _| \\___| _| |_| _|
                V %s \n
                """,Constants.VERSION);

        try{
            Saisir.openScanner();
            Kinomichi prog = new Kinomichi();
            prog.run();
        }finally {
            Saisir.closeScanner();
        }
    }
}
