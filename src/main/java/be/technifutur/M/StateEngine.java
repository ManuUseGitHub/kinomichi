package be.technifutur.M;

public class StateEngine {

    private States currentState;

    public StateEngine(States state) {
        this.currentState = state;
    }

    public States getCurrentState() {
        return currentState;
    }

    public void apply(int i) {
        this.currentState = switch(i){
            case 1 -> States.PLAGE_MANAGEMENT;
            case 2 -> States.PEOPLE_MANAGEMENT;
            case 3 -> States.ADMIN_MANAGEMENT;
            default -> currentState;
        };
    }
}
