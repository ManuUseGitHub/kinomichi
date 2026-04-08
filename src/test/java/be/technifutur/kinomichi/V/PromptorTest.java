package be.technifutur.kinomichi.V;

import be.technifutur.kinomichi.C.StateEngine;
import be.technifutur.kinomichi.M.TimeTables;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import store.luniversdemm.common.Saisir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Pattern;

import static be.technifutur.kinomichi.StateEngineHelper.navigateFromHome;
import static be.technifutur.kinomichi.V.Promptor.*;
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
                Arguments.of(3,"Administration"),
                Arguments.of(4,"Menu principal")
        );
    }
    //endregion

    @BeforeEach
    public void setUp() {
        //System.setOut(new PrintStream(outputStreamCaptor));
        Promptor.setStateEngine(StateEngine.getInstance());
        navigateFromHome();
        Promptor.setCurrentMenu();
    }

    @Test
    void displayingThePlagesMenuWhenTheStateChanged() throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            Promptor.setStateEngine(engine);

            engine.apply(1);
            Promptor.displayMenu();
        });
        String [] lines = text.split("\n");
        assertTrue(Pattern.matches(".*Gestion des plages.*",lines[1]));
    }

    @ParameterizedTest
    @MethodSource("actionsWithResultingMenus")
    void displayingTheCorrectMenuWhenTheStateChanged(int event, String menuTitle) throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            Promptor.setStateEngine(engine);

            engine.apply(event);
            Promptor.displayMenu();
        });
        String [] lines = text.split("\n");
        assertTrue(Pattern.matches(".*"+menuTitle+".*",lines[1]));
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
            engine.apply(Constants.BACK_CODE);
            Promptor.displayMenu();
        });
        assertTrue(Pattern.matches(".*Menu principal.*",text.split("\n")[1]));
    }

    @ParameterizedTest
    @ValueSource( ints = {1,2,3})
    void itIsPossibleToDisplayTheMainMenuFromABackHomeEventOfBCD(int event) throws Exception {

        String text = tapSystemOut(() -> {
            StateEngine engine = StateEngine.getInstance();
            Promptor.setStateEngine(engine);

            // FRONT EVENT navigation to (...)
            engine.apply(event);

            // BACK EVENT
            engine.apply(Constants.GO_HOME_CODE_GRANTED);
            Promptor.displayMenu();
        });
        assertTrue(Pattern.matches(".*Menu principal.*",text.split("\n")[1]));
    }

    @Test
    void itIsPossibleToMockTheScanner() {
        try (MockedStatic<Saisir> utilities = Mockito.mockStatic(Saisir.class)) {
            utilities.when(Saisir::scanString).thenReturn("1 2 3");

            TimeTables tts = new TimeTables();
            Promptor.setStateEngine(StateEngine.getInstance());

            List<String> ids =  Promptor.selectItems(tts,"Quelle plages");
            assertEquals( 3,ids.size());
        }
    }

    @Test
    void itIsPossibleToGenerateThePropositionOfAnEnum(){
        displayParticipantsTypesProposition();
    }

    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
        StateEngine engine = StateEngine.getInstance();
        engine.initStateEngine(States.MAIN_MENU);
    }
}