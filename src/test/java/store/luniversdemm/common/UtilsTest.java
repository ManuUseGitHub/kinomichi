package store.luniversdemm.common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void canMatchMultipleSelections(){
        List arr = new ArrayList<Integer>();
        Utils.onMatches("(\\d+)","1 2 3 4 5 6 7", m -> {
            arr.add(m.group(1));
        });
        assertEquals(7,arr.size());
    }
}