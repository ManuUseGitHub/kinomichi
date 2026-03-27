package be.technifutur.C;

import be.technifutur.M.RequestTranslator;
import be.technifutur.V.Promptor;
import store.luniversdemm.common.Saisir;

public class Kinomichi extends Agent {

    private final RequestTranslator requestTranslator;
    public Kinomichi(){
        this.requestTranslator = new RequestTranslator();
    }

    @Override
    public void request(long n) {
        switch ((int) n){
            case 1:
                System.out.println("Hello world");
        }
    }

    public void run() {
        String choice = "";

        while(!":q".equals(choice)) {
            Promptor.getMenuA();
            Promptor.askWhatDo();
            choice = Saisir.scanString();

            request(this.requestTranslator.translate(choice));
        }
    }
}
