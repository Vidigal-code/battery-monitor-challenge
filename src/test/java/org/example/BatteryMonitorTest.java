package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BatteryMonitorTest {

    private BatteryMonitor monitor;

    @BeforeEach
    public void setup() {
        monitor = new BatteryMonitor();
    }

    @Test
    public void testProblemExample() {
        int[] events = {10, -20, 61, -15};
        int result = monitor.getBattery(events);
        System.out.println("[testProblemExample] Resultado da bateria: " + result);
        assertEquals(85, result, "Battery after example sequence of events");
    }

    @Test
    public void testNoEvents() {
        int[] events = {};
        int result = monitor.getBattery(events);
        System.out.println("[testNoEvents] Resultado da bateria: " + result);
        assertEquals(50, result, "No events, battery remains at 50%");
    }

    @Test
    public void testOnlyCharging() {
        int[] events = {5, 10, 20};
        int result = monitor.getBattery(events);
        System.out.println("[testOnlyCharging] Resultado da bateria: " + result);
        assertEquals(85, result, "Only charging, battery increases correctly");
    }

    @Test
    public void testOnlyUsage() {
        int[] events = {-10, -20, -15};
        int result = monitor.getBattery(events);
        System.out.println("[testOnlyUsage] Resultado da bateria: " + result);
        assertEquals(5, result, "Only usage, battery decreases correctly");
    }

    @Test
    public void testUpperLimit() {
        int[] events = {100};
        int result = monitor.getBattery(events);
        System.out.println("[testUpperLimit] Resultado da bateria: " + result);
        assertEquals(100, result, "Charging beyond 100% should be capped");
    }

    @Test
    public void testLowerLimit() {
        int[] events = {-60};
        int result = monitor.getBattery(events);
        System.out.println("[testLowerLimit] Resultado da bateria: " + result);
        assertEquals(0, result, "Usage beyond 0% should be capped");
    }

    @Test
    public void testAlternatingEvents() {
        int[] events = {20, -10, 30, -25, 15};
        int result = monitor.getBattery(events);
        System.out.println("[testAlternatingEvents] Resultado da bateria: " + result);
        assertEquals(80, result, "Alternating usage and charging");
    }

    @Test
    public void testZeroEvent() {
        int[] events = {0, 10, 0, -5};
        int result = monitor.getBattery(events);
        System.out.println("[testZeroEvent] Resultado da bateria: " + result);
        assertEquals(55, result, "Zero events do not change battery");
    }
}
