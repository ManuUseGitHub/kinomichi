package be.technifutur.M;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StateEngineTest {

    @Test
    public void theCreationOfInstanceInitiatesAState(){
        StateEngine engine = new StateEngine(States.MAIN_MENU);

        assertEquals("a",engine.getCurrentState());
    }

    @Test
    public void aCompriseActionLeadsToAttendedNavigation(){
        StateEngine engine = new StateEngine(States.MAIN_MENU);
        engine.apply(1);

        assertEquals("b",engine.getCurrentState());
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
        StateEngine engine = new StateEngine(States.MAIN_MENU);
        engine.apply(entry);

        assertEquals(state.getValue(),engine.getCurrentState());
    }
}