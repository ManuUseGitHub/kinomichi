package be.technifutur.kinomichi;

import be.technifutur.kinomichi.C.Kinomichi;
import store.luniversdemm.common.Saisir;

import static be.technifutur.kinomichi.V.Promptor.getTitle;

public class Main {
    static void main(String... args){
        getTitle();

        try{
            Saisir.openScanner();
            new Kinomichi().run();
        }finally {
            Saisir.closeScanner();
        }
    }
}
