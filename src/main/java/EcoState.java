public class EcoState implements SystemState {
    @Override
    public String handleStateChange() {
        return "System is in ECO MODE. Only essential systems are running.";
    }
}