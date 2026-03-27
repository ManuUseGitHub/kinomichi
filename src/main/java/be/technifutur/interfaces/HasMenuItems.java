package be.technifutur.interfaces;

import java.util.stream.Stream;

public interface HasMenuItems {
    Stream<String> getItems();
}
