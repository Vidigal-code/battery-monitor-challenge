package org.example.functions.updater;

/**
 *
 * Português
 *
 * Responsável por atualizar o nível da bateria somando a variação calculada.
 */

/**
 *
 * English
 *
 * Responsible for updating the battery level by adding the calculated variation.
 */
public class BatteryLevelUpdater {

    /**
     *
     * Português
     *
     * Atualiza o nível atual da bateria com a variação fornecida.
     *
     * @param currentLevel Nível atual da bateria.
     * @param delta        Variação a ser aplicada (pode ser positiva ou negativa).
     * @return Novo nível da bateria (ainda sem aplicar limites).
     */

    /**
     *
     * English
     *
     * Updates the current battery level with the given variance.
     *
     * @param currentLevel Current battery level.
     * @param delta Variance to apply (can be positive or negative).
     * @return New battery level (no thresholds applied yet).
     */
    public int updateBatteryLevel(final int currentLevel, final int delta) {
        return currentLevel + delta;
    }
}
