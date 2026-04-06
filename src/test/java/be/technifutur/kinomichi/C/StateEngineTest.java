package be.technifutur.kinomichi.C;

import be.technifutur.kinomichi.SavableImpl;
import be.technifutur.kinomichi.V.Promptor;
import be.technifutur.kinomichicommon.C.States;
import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.Savable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static be.technifutur.kinomichi.StateEngineHelper.navigateFromHome;
import static org.junit.jupiter.api.Assertions.*;

class StateEngineTest {

    public StateEngineTest(){

    }

    @BeforeEach
    public void tearUp(){
        Promptor.setStateEngine(StateEngine.getInstance());
        navigateFromHome();
        Promptor.setCurrentMenu();

    }

    @Test
    public void theCreationOfInstanceInitiatesAState(){
        assertEquals("a",StateEngine.getInstance().getCurrentState().getValue());
    }

    @Test
    public void aCompriseActionLeadsToAttendedNavigation(){
        StateEngine.getInstance().apply(1);
        assertEquals("b",StateEngine
                .getInstance()
                .getCurrentState()
                .getValue());
    }

    static List<Arguments> actionsForNavigation(){
        return List.of(
                Arguments.of(1,States.PLAGE_MANAGEMENT),
                Arguments.of(2,States.PEOPLE_MANAGEMENT),
                Arguments.of(3,States.ADMIN_MANAGEMENT)
        );
    }

    @ParameterizedTest
    @MethodSource("actionsForNavigation")
    public void compriseActionOnMainMenuLeadToAttendedNavigation(int entry, States state){
        StateEngine.getInstance().apply(entry);

        assertEquals(state.getValue(),StateEngine.getInstance().getCurrentState().getValue());
    }

    @Test
    public void aNonCompriseActionDoesNotChangeTheNavigation(){
        StateEngine.getInstance().apply(Integer.MAX_VALUE);

        assertEquals("a",StateEngine.getInstance().getCurrentState().getValue());
    }

    @Test
    void cantInvokeQuitEventFromMainMenu(){
        StateEngine.getInstance().apply(Constants.EXIT_CODE);
        assertNull(StateEngine.getInstance().getCurrentState());
    }

    @ParameterizedTest
    @MethodSource("actionsForNavigation")
    void cannotInvokeQuitEventFromOtherThanMainMenu(int entry, States state){
        StateEngine.getInstance().apply(entry);
        StateEngine.getInstance().apply(Constants.EXIT_CODE);
        assertEquals(state,StateEngine.getInstance()
                .getCurrentState());
    }

    @AfterEach
    public void tearDown(){
        StateEngine.getInstance().initStateEngine(States.MAIN_MENU);
    }

    @Test
    void anUnsavedPlageYieldsAConfirmationPageBeforeGoingHome(){
        Savable savableImpl = new SavableImpl();
        RequestTranslator req = new RequestTranslator();
        StateEngine.getInstance().apply(1); // -> B
        StateEngine.getInstance().apply(1); // -> B1
        savableImpl.change();                     // -> B1 !
        StateEngine.getInstance().apply(
                req.translate("a",savableImpl) // -> -A
        );

        assertEquals("-a",StateEngine.getInstance().getCurrentState().getValue());
    }
}