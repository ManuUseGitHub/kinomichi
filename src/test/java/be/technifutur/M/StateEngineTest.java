package be.technifutur.M;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateEngineTest {

    @Test
    public void theCreationOfInstanceInitiatesAState(){
        StateEngine engine = new StateEngine(States.MAIN_MENU);

        assertEquals("a",engine.getCurrentState());
    }

    @Test
    public void aCompriseActionLeadToAttendedNavigation(){
        StateEngine engine = new StateEngine(States.MAIN_MENU);
        engine.apply(1);

        assertEquals("b",engine.getCurrentState());
    }

}