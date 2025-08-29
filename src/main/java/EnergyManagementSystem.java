public class EnergyManagementSystem {

    private SystemState currentState;
    private PricingStrategy currentPricingStrategy;

    // Constructor to set initial state and policy
    public EnergyManagementSystem() {
        this.currentState = new ActiveState();
        this.currentPricingStrategy = new StandardPricingStrategy();
    }

    public void setState(SystemState state) {
        this.currentState = state;
        System.out.println(this.currentState.handleStateChange());
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.currentPricingStrategy = strategy;
    }

    public String getCurrentStatus() {
        // Delegate the task of getting the status message to the current state object.
        return this.currentState.handleStateChange();
    }

    public int calculateEnergyCost(int units) {
        // Delegate the task of calculating the cost to the current strategy object.
        return this.currentPricingStrategy.calculateCost(units);
    }
}