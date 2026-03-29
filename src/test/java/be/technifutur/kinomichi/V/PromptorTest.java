package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichicommon.C.States;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Pattern;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;


class PromptorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    //region Method sources
    public static List<Arguments> actionsWithResultingMenus(){
        return List.of(
                Arguments.of(1,"Gestion des plages"),
                Arguments.of(2,"Gestion des participants"),
                Arguments.of(3,"Administration des données"),
                Arguments.of(4,"Menu principal")
        );
    }
    //endregion

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        StateEngine engine = StateEngine.getInstance();
        engine.initStateEngine(States.MAIN_MENU);
    }

    @Test
    void displayingThePlagesMenuWhenTheStateChanged() throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            engine.apply(-996); // force getting back home because of the singleton

            Promptor.setStateEngine(engine);

            engine.apply(1);
            Promptor.getMenu();
        });
        assertTrue(Pattern.matches(".*Gestion des plages.*",text.split("\n")[0]));
    }

    @ParameterizedTest
    @MethodSource("actionsWithResultingMenus")
    void displayingTheCorrectMenuWhenTheStateChanged(int event, String menuTitle) throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            engine.apply(-996); // force getting back home because of the singleton
            Promptor.setStateEngine(engine);

            engine.apply(event);
            Promptor.getMenu();
        });
        assertTrue(Pattern.matches(".*"+menuTitle+".*",text.split("\n")[0]));
    }

    @ParameterizedTest
    @ValueSource( ints = {1,2,3})
    void itIsPossibleToDisplayTheMainMenuFromABackNavigationOfBCD(int event) throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            Promptor.setStateEngine(engine);

            // FRONT EVENT navigation to (...)
            engine.apply(event);

            // BACK EVENT
            engine.apply(-996);
            Promptor.getMenu();
        });
        assertTrue(Pattern.matches(".*Menu principal.*",text.split("\n")[0]));
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
        StateEngine engine = StateEngine.getInstance();
        engine.initStateEngine(States.MAIN_MENU);
    }


}