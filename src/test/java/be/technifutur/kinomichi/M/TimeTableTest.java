package be.technifutur.kinomichi.M;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {
    @Test
    void itIsPossibleToBuildATimeTableWithAValidDate(){
        TimeTable timeTable = new TimeTable.Builder("échauffement")
                .date("01/01/1993")
                .build();

        assertNotNull(timeTable.getDate());
    }

    public static List<Integer> months() {
        return IntStream.range(0, 12)
                .boxed()
                .toList();
    }

    @ParameterizedTest
    @MethodSource("months")
    void itIsPossibleToBuildATimeTableWithAnOverShoutedDate(int month){
        TimeTable timeTable = new TimeTable.Builder("échauffement 2")
                .date("31/"+(month+1)+"/1993")
                .build();

        assertNotNull(timeTable.getDate());
    }
}