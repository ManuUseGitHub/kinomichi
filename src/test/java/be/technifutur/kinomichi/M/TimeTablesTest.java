package be.technifutur.kinomichi.M;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TimeTablesTest {
    @Test
    void itIsPossibleToAddATimeTableWithTheLastId(){
        TimeTables tts = new TimeTables();
        tts.addItem(new TimeTable.Builder("activity"));

        assertNotNull(tts.getItemById(1));
    }

    @Test
    void allGeneratedIdsAreFromAnIncrementation(){
        TimeTables tts = new TimeTables();
        IntStream.range(0,10).forEach(n -> {
            tts.addItem(new TimeTable.Builder("activity "+(n+1)));
        });

        IntStream.range(0,10).forEach(n -> {
            System.out.println(tts.getItemById(n+1).getActivity());
            assertNotNull(tts.getItemById(n+1));
        });
    }

    @Test
    void removedTimeTablesDontDisruptTheIdGeneration(){
        TimeTables tts = new TimeTables();
        IntStream.range(0,10).forEach(n -> {
            tts.addItem(new TimeTable.Builder("activity "+(n+1)));
        });

        tts.removeItem(5);
        tts.addItem(new TimeTable.Builder("activity x"));

        tts.getItems().forEach(t -> {
            System.out.println(t.getActivity());
        });
    }
}