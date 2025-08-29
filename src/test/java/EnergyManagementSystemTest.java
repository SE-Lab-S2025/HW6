import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnergyManagementSystemTest {

    private EnergyManagementSystem system;

    @BeforeEach
    void setUp() {
        system = new EnergyManagementSystem();
    }

    @Test
    void testInitialStateAndPolicy() {
        // The system should start in Active state and Standard tariff 
        assertEquals("System is ACTIVE. All systems are running.", system.getCurrentStatus());
        // 10 units should cost 5000 with Standard tariff
        assertEquals(5000, system.calculateEnergyCost(10));
    }

    @Test
    void testChangeState() {
        system.setState(new EcoState());
        assertEquals("System is in ECO MODE. Only essential systems are running.", system.getCurrentStatus());
    }

    @Test
    void testChangePricingStrategy() {
        system.setPricingStrategy(new PeakHoursPricingStrategy());
        // After changing to Peak, 10 units should cost 10000
        assertEquals(10000, system.calculateEnergyCost(10));
    }
}