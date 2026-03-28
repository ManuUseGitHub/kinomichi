package be.technifutur.M;

public class StateEngine {

    private String currentState;

    public StateEngine(States state) {
        this.currentState = state.getValue();
    }

    public String getCurrentState() {
        return currentState;
    }

    public void apply(int i) {
        if(i == 1){
            this.currentState = States.PLAGE_MANAGEMENT.getValue();
        }
    }
}
