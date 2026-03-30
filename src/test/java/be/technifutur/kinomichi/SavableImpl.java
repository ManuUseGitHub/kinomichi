package be.technifutur.kinomichi;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.interfaces.Savable;

public class SavableImpl implements Savable {
    boolean saved = true;
    private final StateEngine stateEngine;

    public SavableImpl() {
        this.stateEngine = StateEngine.getInstance();
    }

    @Override
    public boolean save() {
        saved = true;
        return saved;
    }

    @Override
    public boolean isSaved() {
        return saved;
    }

    @Override
    public void change() {
        saved = false;
    }
}
