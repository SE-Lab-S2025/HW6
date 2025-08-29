public class GreenModePricingStrategy implements PricingStrategy {
    @Override
    public int calculateCost(int units) {
        return units * 300; // 300 per unit [cite: 23]
    }
}