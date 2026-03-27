package be.technifutur.M;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RequestTranslatorTest {
    @Test
    void testSelectingTheAddParticipantSucceeds() {
        RequestTranslator req = new RequestTranslator();
        var result = req.translate("1");
        Assertions.assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testSelectingTheTheThreeOptionsSucceeds(int choice) {
        RequestTranslator req = new RequestTranslator();
        var result = req.translate(String.valueOf(choice));
        Assertions.assertEquals(choice, result);
    }
}
