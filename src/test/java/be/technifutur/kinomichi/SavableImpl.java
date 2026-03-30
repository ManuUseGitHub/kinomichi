package be.technifutur.kinomichi;

import be.technifutur.kinomichicommon.interfaces.Savable;

public class SavableImpl implements Savable {
    boolean saved = true;

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
