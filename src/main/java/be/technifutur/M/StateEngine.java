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
        this.currentState = switch(i){
            case 1 -> States.PLAGE_MANAGEMENT.getValue();
            case 2 -> States.PEOPLE_MANAGEMENT.getValue();
            case 3 -> States.ADMIN_MANAGEMENT.getValue();
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
    }
}
