package be.technifutur.C;

public class Kinomichi extends Agent {

    @Override
    public void request(long n) {
        switch ((int) n){
            case 1:
                System.out.println("Hello world");
        }
    }
}
