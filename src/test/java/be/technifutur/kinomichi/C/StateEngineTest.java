package be.technifutur.kinomichi.C;

import be.technifutur.kinomichicommon.C.States;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static be.technifutur.kinomichi.StateEngineHelper.navigateFromHome;
import static org.junit.jupiter.api.Assertions.*;

class StateEngineTest {

    @BeforeEach
    public void tearUp(){
        StateEngine.getInstance().initStateEngine(States.MAIN_MENU);
        navigateFromHome();
    }

    @Test
    public void theCreationOfInstanceInitiatesAState(){
        navigateFromHome();

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
        StateEngine.getInstance().apply(-999);
        assertNull(StateEngine.getInstance().getCurrentState());
    }

    @ParameterizedTest
    @MethodSource("actionsForNavigation")
    void cannotInvokeQuitEventFromOtherThanMainMenu(int entry, States state){
        StateEngine.getInstance().apply(entry);
        StateEngine.getInstance().apply(-999);
        assertEquals(state.getValue(),StateEngine.getInstance().getCurrentState().getValue());
    }

    @AfterEach
    public void tearDown(){
        StateEngine.getInstance().initStateEngine(States.MAIN_MENU);
    }

}