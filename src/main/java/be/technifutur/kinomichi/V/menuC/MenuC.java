package be.technifutur.kinomichi.V.menuC;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public class MenuC extends Menu implements HasMenuItems {
    public MenuC() {
        super(States.PEOPLE_MANAGEMENT.getLabel(), Arrays.stream(new String[]{
                "Ajout de participants",
                "Suppression de participants",
                "Modification d'un participant",
                "Charger des participants",
                "Sauvegarder les participants",
                "Listing des participants"
        }));
    }
}
