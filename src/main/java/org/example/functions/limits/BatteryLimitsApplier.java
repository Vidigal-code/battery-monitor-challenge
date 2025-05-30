package org.example.functions.limits;

/**
 *
 *Português
 *
 * Responsável por garantir que o nível da bateria permaneça entre 0% e 100%.
 */

/**
 *
 * English
 *
 * Responsible for ensuring that the battery level remains between 0% and 100%.
 */
public class BatteryLimitsApplier {

    private static final int MIN_BATTERY = 0;
    private static final int MAX_BATTERY = 100;

    /**
     *
     * Português
     *
     * Aplica os limites superior e inferior ao nível da bateria.
     *
     * @param batteryLevel Nível atual da bateria.
     * @return Nível corrigido da bateria (entre 0 e 100).
     */

    /**
     *
     * English
     *
     * Applies upper and lower limits to the battery level.
     *
     * @param batteryLevel Current battery level.
     * @return Corrected battery level (between 0 and 100).
     */
    public int applyBatteryLimits(final int batteryLevel) {
        if (batteryLevel > MAX_BATTERY) {
            return MAX_BATTERY;
        }
        return Math.max(batteryLevel, MIN_BATTERY);
    }
}
