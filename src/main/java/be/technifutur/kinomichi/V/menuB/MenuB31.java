package be.technifutur.kinomichi.V.menuB;

import be.technifutur.kinomichi.V.Menu;
import be.technifutur.kinomichicommon.interfaces.HasMenuItems;

import java.util.Arrays;

public final class MenuB31 extends Menu implements HasMenuItems {
    public MenuB31() {
        super("[B] Edition d'une plage", Arrays.stream(new String[]{
        }));
    }

    @Override
    public String getMenuFooter(){
        return "";
    }
}
