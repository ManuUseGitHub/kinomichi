package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.Savable;
import store.luniversdemm.common.Saisir;

public class Kinomichi extends Agent implements Savable {
    private final StateEngine stateEngine;
    private final RequestTranslator requestTranslator;
    private boolean saved = true;

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

        while(Constants.EXIT_CODE != data) {
            Promptor.getMenu();
            Promptor.askWhatDo();

            data = this.requestTranslator.translate(Saisir.scanString(),this);
            stateEngine.apply(data);
            request(data);
        }
    }

    @Override
    public boolean save() {
        this.saved = true;
        return saved;
    }

    @Override
    public boolean isSaved() {
        return saved;
    }

    @Override
    public void change() {
        this.saved = false;
    }
}
