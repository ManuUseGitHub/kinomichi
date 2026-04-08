package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static store.luniversdemm.common.Utils.onReadTextFile;

class PlageVersionManagerServiceTest {

    TimeTables getPopulateddTimeTables(){
        TimeTables tts = new TimeTables();
        tts.addItem(new TimeTable.Builder("Katori")
                .description("Des exercices avec un bokken son prévu dans la découverte des amis du kinomichi")
                .date("10/10/2027")
                .start("10:15")
                .duration("10")
                .animator("Polus")
        );
        return tts;
    }

    @Test
    void aCollectionOfTimeTablesCanBeSaved(){
        TimeTables tts = getPopulateddTimeTables();
        String fileName = "file.ser";

        PlageVersionManagerService pvms = new PlageVersionManagerService();
        pvms.save(tts,fileName);

        assertTrue(new File(fileName).exists());
    }

    @Test
    void aCollectionOfTimeTablesCanBeLoaded(){
        TimeTables tts = getPopulateddTimeTables();
        String fileName = "file.ser";

        PlageVersionManagerService pvms = new PlageVersionManagerService();

        pvms.save(tts,fileName);
        TimeTables tts2 = pvms.load(fileName);

        assertNotNull(tts2.getItemById(1));
    }

    @Test
    void loadByTextSource() {
        PlageVersionManagerService pvms = new PlageVersionManagerService();

        onReadTextFile("saveState.kino",(textContent) -> {
            TimeTables result = pvms.loadByTextSource(textContent);
            TimeTable tt = result.getItemById(1);
            assertNotNull(tt);

            assertEquals(4,result.getItems().size());
            assertEquals("Totaly",tt.getActivity());
            assertEquals(1,tt.getId());
            assertEquals("spies",tt.getDescription());
            assertEquals("tom tom",tt.getAnimator());

            assertEquals(10,tt.getDate().getDayOfMonth());
            assertEquals(10,tt.getDate().getMonthValue());
            assertEquals(2027,tt.getDate().getYear());

            assertEquals(10,tt.getStart().getHour());
            assertEquals(15,tt.getStart().getMinute());

            assertEquals(10,tt.getStart().getHour());
            assertEquals(45,tt.getEnd().getMinute());
        });
    }
}