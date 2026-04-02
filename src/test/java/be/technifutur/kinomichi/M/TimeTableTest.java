package be.technifutur.kinomichi.M;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableTest {
    //region Method sources
    public static List<Integer> months() {
        return IntStream.range(0, 12)
                .boxed()
                .toList();
    }
    public static List<Arguments> startsWithDurations(){
        return List.of(
                Arguments.of("10:30","15",10,45),
                Arguments.of("10:45","30",11,15),
                Arguments.of("11:15","45",12,0),
                Arguments.of("13:00","30",13,30),
                Arguments.of("13:30","1:30",15,0),
                Arguments.of("15:00","120",17,0),

                // The next hour is after midnight
                Arguments.of("23:59","10",0,9)
        );
    }
    //endregion

    @Test
    void itIsPossibleToBuildATimeTableWithAValidDate(){
        TimeTable timeTable = new TimeTable.Builder("échauffement")
                .date("01/01/1993")
                .build();

        assertNotNull(timeTable.getDate());
    }

    @Test
    void itIsPossibleToBuildATimeTableWithAvlidStartTime(){
        TimeTable timeTable = new TimeTable.Builder("atelier gestion du temps")
                .start("10:30")
                .build();

        assertNotNull(timeTable.getStart());
    }

    @ParameterizedTest
    @MethodSource("startsWithDurations")
    void itIsPossibleToBuildATimeTableWithADurationsWithStartTime(String start, String duration, int expectedHourEnd, int expectedMinuteEnd){
        TimeTable timeTable = new TimeTable.Builder("atelier gestion du temps")
                .start(start)
                .duration(duration)
                .build();

        assertEquals(expectedHourEnd,timeTable.getEnd().getHour());
        assertEquals(expectedMinuteEnd,timeTable.getEnd().getMinute());
    }

    @Test
    void itIsPossibleToAddATimeTableWithADescription(){
        TimeTable timeTable = new TimeTable.Builder("atelier préparation d'une présentation")
                .description("C'est un atelier où on va apprendre à bien se présenter de façon martial")
                .build();
        assertEquals("C'est un atelier où on va apprendre à bien se présenter de façon martial",timeTable.getDescription());
    }

    @Test
    void everyTimetableHasAnActivity(){
        TimeTable timeTable = new TimeTable.Builder("test")
                .build();
        assertEquals("test",timeTable.getActivity());
    }

    @Test
    void aTimeTableHasAnAnimator(){
        TimeTable timeTable = new TimeTable.Builder("test2")
                .animator("Chuck Norris")
                .build();
        assertEquals("Chuck Norris",timeTable.getAnimator());
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