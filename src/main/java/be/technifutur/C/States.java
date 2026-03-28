package be.technifutur.C;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public enum States {
    MAIN_MENU("a", "Menu principal"),

    PLAGE_MANAGEMENT("b","Gestion des plages"),
    PEOPLE_MANAGEMENT("c","Gestion des participants"),
    ADMIN_MANAGEMENT("d", "Administration"),

    PLAGE_ADDING("b1", "Ajout de plage(s)"),
    PLAGE_DELETING("b2", "Suppression de plage(s)"),
    PLAGE_EDIT("b3", "Edition d'une plage"),
    PLAGE_LISTING("b4", "Liste des plages"),

    PEOPLE_ADDING("c1", "Ajout d'un participant"),
    PEOPLE_DELETING("c2", "Suppression"),
    PEOPLE_EDIT("c3", "Edition d'un participant"),
    PEOPLE_LISTING("c4", "Liste des participants");

    private final String value;
    private final String label;

    States(String value,String label){
        this.value = value;
        this.label = label;
    }

    public static States get(String value){
        AtomicReference<States> state = new AtomicReference<>();
        Arrays.stream(values()).filter(v -> v.value.equals(value)).findFirst().ifPresent(state::set);
        return state.get();
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
