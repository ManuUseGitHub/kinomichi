package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuD extends Menu implements HasMenuItems {
    public MenuD() {
        super("Administration des données", Arrays.stream(new String[]{
        }));
    }
}
