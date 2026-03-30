package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.SavableImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class RequestTranslatorTest {

    static SavableImpl savable = new SavableImpl();

    @BeforeEach()
    void tearUp(){
        savable.save();
    }

    //region Method sources
    static List<Arguments> specialEventsForSpecialActions(){
        return List.of(Arguments.of("q", -999,true),
                Arguments.of("r", -998,true),
                Arguments.of("a", -996,false),
                Arguments.of("a", -995,true));
    }
    //endregion

    @Test
    void testSelectingTheAddParticipantSucceeds() {
        RequestTranslator req = new RequestTranslator();
        var result = req.translate("1", savable);
        Assertions.assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSelectingTheTheThreeOptionsSucceeds(int choice) {
        Assertions.assertEquals(choice, new RequestTranslator()
                .translate(String.valueOf(choice), savable)
        );
    }

    @ParameterizedTest(name = "action \"{0}\" yields {1} event")
    @MethodSource("specialEventsForSpecialActions")
    void testSelectingQuitGivesQuitCommand(String action, int event, boolean saved) {
        if(!saved){
            savable.change();
        }
        Assertions.assertEquals(event, new RequestTranslator().translate(action, savable));
    }
}
