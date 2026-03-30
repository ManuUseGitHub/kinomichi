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
                .date("0/"+(month+1)+"/1993")
                .build();

        assertNotNull(timeTable.getDate());
    }

    @Test
    void aTimeTableOfXX_XX_YearFallsBackToFirstOfJanuaryOfThatDate(){
        TimeTable timeTable = new TimeTable.Builder("échauffement 3")
                .date("0/0/1993")
                .build();
        int m = timeTable.getDate().getMonthValue();
        int d = timeTable.getDate().getDayOfMonth();

        assertEquals(1,d);
        assertEquals(1,m);
    }

    @Test
    void aTimeTableOf50_20_5000FallsBackToFirstOfJanuaryOfThatDate(){
        TimeTable timeTable = new TimeTable.Builder("échauffement 4")
                .date("50/20/5000")
                .build();
        int y = timeTable.getDate().getYear();
        int m = timeTable.getDate().getMonthValue();
        int d = timeTable.getDate().getDayOfMonth();

        assertEquals(5000, y);
        assertEquals(31,d);
        assertEquals(12,m);
    }
}