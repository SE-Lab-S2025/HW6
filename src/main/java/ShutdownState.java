public class ShutdownState implements SystemState {
    @Override
    public String handleStateChange() {
        return "System is SHUT DOWN. All systems are off.";
    }
}