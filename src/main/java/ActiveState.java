public class ActiveState implements SystemState {
    @Override
    public String handleStateChange() {
        return "System is ACTIVE. All systems are running.";
    }
}