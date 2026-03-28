package be.technifutur.kinomichi.V;

import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuC extends Menu implements HasMenuItems {
    public MenuC() {
        super("Gestion des participants", Arrays.stream(new String[]{
                "Ajout de participants",
                "Suppression de participants",
                "Modification d'un participant",
                "Listing des participants"
        }));
    }
}
