package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.V.Promptor;
import store.luniversdemm.common.Saisir;

public class Kinomichi extends Agent {
    private StateEngine stateEngin;
    private final RequestTranslator requestTranslator;

    public Kinomichi(){
        this.requestTranslator = new RequestTranslator();
        this.stateEngin = new StateEngine(States.MAIN_MENU);
    }

    @Override
    public void request(long n) {
        switch ((int) n){
            case 1:
                System.out.println("Hello world");
        }
    }

    public void run() {
        long data = 0;

        while(-999 != data) {
            Promptor.getMenuA();
            Promptor.askWhatDo();
            data = this.requestTranslator.translate(Saisir.scanString());

            request(data);
        }
    }
}
