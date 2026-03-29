package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import store.luniversdemm.common.Saisir;

public class Kinomichi extends Agent {
    private final StateEngine stateEngine;
    private final RequestTranslator requestTranslator;

    public Kinomichi(){
        this.requestTranslator = new RequestTranslator();
        stateEngine = StateEngine.getInstance();
        stateEngine.initStateEngine(States.MAIN_MENU);

        Promptor.setStateEngine(this.stateEngine);
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
            Promptor.getMenu();
            Promptor.askWhatDo();
            data = this.requestTranslator.translate(Saisir.scanString());
            stateEngine.apply(data);
            request(data);
        }
    }
}
