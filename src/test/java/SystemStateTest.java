import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemStateTest {

    @Test
    void testActiveState() {
        SystemState active = new ActiveState();
        // The message should confirm all systems are on [cite: 27, 31]
        assertEquals("System is ACTIVE. All systems are running.", active.handleStateChange());
    }

    @Test
    void testEcoModeState() {
        SystemState eco = new EcoState();
        // The message should confirm only essential systems are on [cite: 28, 31]
        assertEquals("System is in ECO MODE. Only essential systems are running.", eco.handleStateChange());
    }

    @Test
    void testShutdownState() {
        SystemState shutdown = new ShutdownState();
        // The message should confirm all systems are off [cite: 30, 31]
        assertEquals("System is SHUT DOWN. All systems are off.", shutdown.handleStateChange());
    }
}