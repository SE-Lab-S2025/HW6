public class StandardPricingStrategy implements PricingStrategy {
    @Override
    public int calculateCost(int units) {
        return units * 500; // 500 per unit [cite: 23]
    }
}