package be.technifutur.M;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTranslatorTest {
    @Test
    void testSelectingTheAddParticipantSucceeds() {
        RequestTranslator req = new RequestTranslator();
        var result = req.translate("1");
        Assertions.assertEquals(1,result);
    }
}
