package be.technifutur.C;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class StatesTest {

    @Test
    public void itIsPossibleToGetAStateFromAString(){
        States state = States.get("a");
        Assertions.assertEquals(States.MAIN_MENU,state);
    }

    @Test
    public void theEnumCanGivesThreeDifferentInfos(){
        Assertions.assertNotEquals(States.MAIN_MENU.getValue(),States.MAIN_MENU.toString());
        Assertions.assertNotEquals(States.MAIN_MENU.getLabel(),States.MAIN_MENU.toString());
        Assertions.assertNotEquals(States.MAIN_MENU.getLabel(),States.MAIN_MENU.getValue());
    }

    static Stream<Arguments> navigationEnumExpectations(){
        return Stream.of(
                Arguments.of("a",States.MAIN_MENU),
                Arguments.of("b",States.PEOPLE_MANAGEMENT),
                Arguments.of("c",States.PLAGE_MANAGEMENT)
        );
    }

    @ParameterizedTest
    @MethodSource("navigationEnumExpectations")
    public void itIsPossibleToGetAStateFromAnyString(String value, States expected){
        States state = States.get(value);
        Assertions.assertEquals(expected,state);
    }
}
