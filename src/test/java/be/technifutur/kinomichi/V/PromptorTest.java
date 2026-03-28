package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.States;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;


class PromptorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void displayingThePlagesMenuWhenTheStateChanged() throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = new StateEngine(States.MAIN_MENU);
            Promptor.setStateEngine(engine);

            engine.apply(1);
            Promptor.getMenu();
        });
        assertTrue(Pattern.matches(".*Gestion des plages.*",text.split("\n")[0]));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}