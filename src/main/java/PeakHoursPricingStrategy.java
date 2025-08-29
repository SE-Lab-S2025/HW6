public class PeakHoursPricingStrategy implements PricingStrategy {
    @Override
    public int calculateCost(int units) {
        return units * 1000; // 1000 per unit [cite: 23]
    }
}