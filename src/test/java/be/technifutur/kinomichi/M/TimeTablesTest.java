package be.technifutur.kinomichi.M;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TimeTablesTest {
    @Test
    void itIsPossibleToAddATimeTableWithTheLastId(){
        TimeTables tts = new TimeTables();
        tts.addTimeTable(new TimeTable.Builder("activity"));

        assertNotNull(tts.getTimeTableById(1));
    }

    @Test
    void allGeneratedIdsAreFromAnIncrementation(){
        TimeTables tts = new TimeTables();
        IntStream.range(0,10).forEach(n -> {
            tts.addTimeTable(new TimeTable.Builder("activity "+(n+1)));
        });

        IntStream.range(0,10).forEach(n -> {
            System.out.println(tts.getTimeTableById(n+1).getActivity());
            assertNotNull(tts.getTimeTableById(n+1));
        });
    }

    @Test
    void removedTimeTablesDontDisruptTheIdGeneration(){
        TimeTables tts = new TimeTables();
        IntStream.range(0,10).forEach(n -> {
            tts.addTimeTable(new TimeTable.Builder("activity "+(n+1)));
        });

        tts.removeTimetable(5);
        tts.addTimeTable(new TimeTable.Builder("activity x"));

        tts.getTimeTables().forEach(t -> {
            System.out.println(t.getActivity());
        });
    }
}