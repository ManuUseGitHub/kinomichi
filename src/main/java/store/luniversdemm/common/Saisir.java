package store.luniversdemm.common;

import java.util.Scanner;

public class Saisir {

    private static Scanner scan;
    public static void openScanner(){
        scan = new Scanner(System.in);
    }
    public static void closeScanner(){
        if(scan != null){
            scan.close();
        }
    }

    private Saisir(){
        scan = new Scanner(System.in);
    }

    /**
     * Permet à l'utilisateur d'encoder un nombre
     * @param scan une référence vers le scanner
     * @return un nombre qui vaut la valeur rentrée
     * Dans le cas où une valeur entrée ne peut être parsé en entier
     * la valeur de retour est égale à Integer.MIN_VALUE (-2147483648) ou -2^31^ pour signifier
     * un NOT a NUMBER
     */
    public static int scanNumber() {
        int val = Integer.MIN_VALUE;
        try {
            val = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Ceci nétait pas un nombre !");
        }
        return val;
    }

    /**
     * Permet à l'utilisateur d'encoder un nombre
     *
     * @param scan une référence vers le scanner
     * @return un nombre qui vaut la valeur rentrée
     *         Dans le cas où une valeur entrée ne peut être parsé en entier
     *         la valeur de retour est égale à Integer.MIN_VALUE
     *         (0x0.0000000000001P-1022) ou
     *         -2^-1074^ pour signifier
     *         un NOT a NUMBER
     */
    public static double scanDouble() {
        double val = Double.MIN_VALUE;
        try {
            val = Double.parseDouble(scan.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Ceci nétait pas un nombre !");
        }
        return val;
    }

    /**
     * Permet à l'utilisateur d'encoder une chaine
     *
     * @param scan une référence vers le scanner
     * @return
     */
    public static String scanString() {
        return scan.nextLine();
    }

    /**
     * Permet d'attendre que l'utilisateur soit mentalement pret passer àà la suite
     *
     * @param scan une référence vers le scanner
     */
    public static void attendreEnter() {
        System.out.println("faites <Enter> pour continuer");
        Saisir.scanString();
    };
}
