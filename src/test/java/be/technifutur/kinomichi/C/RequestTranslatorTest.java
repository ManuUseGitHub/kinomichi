package be.technifutur.kinomichi.C;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RequestTranslatorTest {

    //region Method sources
    static List<Arguments> specialEventsForSpecialActions(){
        return List.of(Arguments.of("q", -999),
                Arguments.of("r", -998),
                Arguments.of("a", -996));
    }
    //endregion

    @Test
    void testSelectingTheAddParticipantSucceeds() {
        RequestTranslator req = new RequestTranslator();
        var result = req.translate("1");
        Assertions.assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSelectingTheTheThreeOptionsSucceeds(int choice) {
        Assertions.assertEquals(choice, new RequestTranslator()
                .translate(String.valueOf(choice))
        );
    }

    @ParameterizedTest(name = "action '{0}' yields {1} event")
    @MethodSource("specialEventsForSpecialActions")
    void testSelectingQuitGivesQuitCommand(String action, int event) {
        Assertions.assertEquals(event, new RequestTranslator().translate(action));
    }
}
