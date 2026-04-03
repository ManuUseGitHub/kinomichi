package be.technifutur.kinomichi.S;

import be.technifutur.kinomichi.M.TimeTable;
import be.technifutur.kinomichi.M.TimeTables;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PlageVersionManagerServiceTest {

    TimeTables getPopulateddTimeTables(){
        TimeTables tts = new TimeTables();
        tts.addTimeTable(new TimeTable.Builder("Katori")
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

        assertNotNull(tts2.getTimeTableById(1));
    }

}