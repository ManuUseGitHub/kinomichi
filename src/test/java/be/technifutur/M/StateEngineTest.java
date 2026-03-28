package be.technifutur.M;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateEngineTest {

    @Test
    public void theCreationOfInstanceInitiatesAState(){
        StateEngine engine = new StateEngine(States.MAIN_MENU);

        assertEquals("a",engine.getCurrentState());
    }

}