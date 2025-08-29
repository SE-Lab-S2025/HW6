import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for our future pricing strategies
public class PricingStrategyTest {

    @Test
    void testStandardPricing() {
        // This class doesn't exist yet, but we define how it should work.
        PricingStrategy standard = new StandardPricingStrategy();
        // Standard tariff is 500 per unit [cite: 23]
        assertEquals(5000, standard.calculateCost(10));
    }

    @Test
    void testPeakHoursPricing() {
        // This class also doesn't exist yet.
        PricingStrategy peak = new PeakHoursPricingStrategy();
        // Peak hours tariff is 1000 per unit [cite: 23]
        assertEquals(10000, peak.calculateCost(10));
    }

    @Test
    void testGreenModePricing() {
        // And this one doesn't exist yet either.
        PricingStrategy green = new GreenModePricingStrategy();
        // Green mode tariff is 300 per unit [cite: 23]
        assertEquals(3000, green.calculateCost(10));
    }
}