package be.technifutur.kinomichi;

import be.technifutur.kinomichi.C.Kinomichi;
import be.technifutur.kinomichi.V.Promptor;
import store.luniversdemm.common.Saisir;

public class Main {
    static void main(String... args){
        Promptor.getTitle();

        try{
            Saisir.openScanner();
            new Kinomichi().run();
        }finally {
            Saisir.closeScanner();
        }
    }
}
