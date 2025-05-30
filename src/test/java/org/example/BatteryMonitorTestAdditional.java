package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BatteryMonitorTestAdditional {

    private BatteryMonitor monitor;

    @BeforeEach
    public void setup() {
        monitor = new BatteryMonitor();
    }

    @Test
    public void testOriginalCase() {
        int[] events = {10, -20, 61, -16};
        int result = monitor.getBattery(events);
        System.out.println("[testOriginalCase] Resultado da bateria: " + result);
        assertEquals(84, result, "Original test case {10, -20, 61, -16}");
    }

    @Test
    public void testOnlyCharging() {
        int[] events = {5, 10};
        int result = monitor.getBattery(events);
        System.out.println("[testOnlyCharging] Resultado da bateria: " + result);
        assertEquals(65, result, "Charging events only");
    }

    @Test
    public void testOnlyUsage() {
        int[] events = {-30, -25};
        int result = monitor.getBattery(events);
        System.out.println("[testOnlyUsage] Resultado da bateria: " + result);
        assertEquals(0, result, "Battery usage events only");
    }

    @Test
    public void testMaxLimitWith60() {
        int[] events = {60};
        int result = monitor.getBattery(events);
        System.out.println("[testMaxLimitWith60] Resultado da bateria: " + result);
        assertEquals(100, result, "Max limit with event 60");
    }

    @Test
    public void testMaxLimitWith20DoesNotReach100() {
        int[] events = {20};
        int result = monitor.getBattery(events);
        System.out.println("[testMaxLimitWith20DoesNotReach100] Resultado da bateria: " + result);
        assertEquals(70, result, "Max limit with event 20, does not reach 100%");
    }

    @Test
    public void testMinLimitWithHeavyUsage() {
        int[] events = {-60};
        int result = monitor.getBattery(events);
        System.out.println("[testMinLimitWithHeavyUsage] Resultado da bateria: " + result);
        assertEquals(0, result, "Min limit with heavy battery usage");
    }

    @Test
    public void testNoEvents() {
        int[] events = {};
        int result = monitor.getBattery(events);
        System.out.println("[testNoEvents] Resultado da bateria: " + result);
        assertEquals(50, result, "Empty or null events");
    }

    @Test
    public void testZeroIncludedEvents() {
        int[] events = {0, 10, 0, -5};
        int result = monitor.getBattery(events);
        System.out.println("[testZeroIncludedEvents] Resultado da bateria: " + result);
        assertEquals(55, result, "Events containing zeros");
    }

    @Test
    public void testMultipleChargingOver100() {
        int[] events = {30, 40, 50};
        int result = monitor.getBattery(events);
        System.out.println("[testMultipleChargingOver100] Resultado da bateria: " + result);
        assertEquals(100, result, "Multiple charges exceeding 100%");
    }

    @Test
    public void testMultipleUsageToZero() {
        int[] events = {-20, -20, -20};
        int result = monitor.getBattery(events);
        System.out.println("[testMultipleUsageToZero] Resultado da bateria: " + result);
        assertEquals(0, result, "Multiple battery usage down to 0%");
    }

    @Test
    public void testAlternatingChargeAndUsage() {
        int[] events = {20, -10, 30, -25, 15};
        int result = monitor.getBattery(events);
        System.out.println("[testAlternatingChargeAndUsage] Resultado da bateria: " + result);
        assertEquals(80, result, "Alternating charging and usage events");
    }
}
