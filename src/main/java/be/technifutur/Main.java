package be.technifutur;

import common.Saisir;

public class Main {
    public static void main(String... args){
        Saisir.openScanner();

        System.out.println("Type hello");
        String hello = Saisir.scanString();

        System.out.println("Type world");
        String world = Saisir.scanString();

        System.out.println("%s %s".formatted(hello,world));

        Saisir.closeScanner();
    }
}
