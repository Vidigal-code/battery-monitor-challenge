package org.example.utills;

import org.example.functions.calculator.BatteryChangeCalculator;

/**
 *
 *Português
 *
 * Utilitário para exibir informações sobre o processamento da bateria.
 */

/**
 *
 *English
 *
 * Utility to display information about battery processing.
 */

public class PrintUtils {

    /**
     *
     * Português
     *
     * Exibe o status inicial da bateria.
     *
     * @param initialLevel Valor inicial da bateria (em %).
     */

    /**
     *
     * English
     *
     * Displays the initial battery status.
     *
     * @param initialLevel Initial battery value (in %).
     */

    public static void printInitialBatteryStatus(final int initialLevel) {
        System.out.println("1. Carga inicial: " + initialLevel + "%");
    }

    /**
     *
     * Português
     *
     * Exibe os detalhes de um evento individual de bateria.
     *
     * @param index                Índice do evento.
     * @param event               Valor do evento (positivo para carga, negativo para uso).
     * @param previousBattery      Nível anterior da bateria.
     * @param change               Variação de bateria causada pelo evento.
     * @param uncappedBatteryLevel Nível antes da aplicação de limites.
     * @param finalBatteryLevel    Nível final da bateria após limites.
     * @param calculator           Calculadora usada para determinar o tipo do evento.
     */

    /**
     *
     * English
     *
     * Displays details of an individual battery event.
     *
     * @param index Event index.
     * @param event Event value (positive for charging, negative for use).
     * @param previousBattery Previous battery level.
     * @param change Battery change caused by the event.
     * @param uncappedBatteryLevel Level before caps were applied.
     * @param finalBatteryLevel Final battery level after caps.
     * @param calculator Calculator used to determine the event type.
     */

    public static void printEventDetails(
            final int index,
            final int event,
            final int previousBattery,
            final int change,
            final int uncappedBatteryLevel,
            final int finalBatteryLevel,
            final BatteryChangeCalculator calculator
    ) {



        /**
         *
         * Português
         *
         * Define uma anotação de limite de bateria com base nos níveis de carga não limitados e finais.
         *
         * <p>
         * A anotação `limitNote` é usada para indicar se o nível de bateria atingiu um dos limites extremos (0% ou 100%)
         * devido a um evento de carregamento ou descarga. Essa informação é útil para entender que, embora o cálculo
         * interno (`uncappedBatteryLevel`) tenha extrapolado os limites normais, o valor final foi ajustado para os
         * limites reais de capacidade da bateria (0% a 100%).
         * </p>
         *
         * <p>
         * A lógica condicional é a seguinte:
         * </p>
         * <ul>
         *   <li>Se o nível de bateria não limitado (`uncappedBatteryLevel`) for maior que 100
         *       <strong>e</strong> o nível final (`finalBatteryLevel`) for 100, significa que o valor foi limitado
         *       ao máximo permitido. Nesse caso, o texto " (limite máximo)" será adicionado.</li>
         *   <li>Se o nível de bateria não limitado for menor que 0
         *       <strong>e</strong> o nível final for 0, significa que o valor foi limitado ao mínimo permitido.
         *       Nesse caso, o texto " (limite mínimo)" será adicionado.</li>
         *   <li>Caso contrário, a string resultante será vazia, indicando que o valor não foi limitado.</li>
         * </ul>
         *
         */

        /**
         *
         * English
         *
         * Sets a battery limit annotation based on the uncapped and final charge levels.
         *
         * <p>
         * The `limitNote` annotation is used to indicate whether the battery level has reached one of the extreme limits (0% or 100%)
         * due to a charging or discharging event. This information is useful to understand that although the internal
         * calculation (`uncappedBatteryLevel`) has exceeded the normal limits, the final value has been adjusted to the
         * actual battery capacity limits (0% to 100%). * </p>
         *
         * <p>
         * The conditional logic is as follows:
         * </p>
         * <ul>
         * <li>If the uncapped battery level (`uncappedBatteryLevel`) is greater than 100
         * <strong>and</strong> the final level (`finalBatteryLevel`) is 100, it means that the value has been capped
         * to the maximum allowed. In this case, the text " (maximum limit)" will be added.</li>
         * <li>If the uncapped battery level is less than 0
         * <strong>and</strong> the final level is 0, it means that the value has been capped to the minimum allowed.
         * In this case, the text " (minimum limit)" will be added.</li>
         * <li>Otherwise, the resulting string will be empty, indicating that the value has not been capped.</li>
         * </ul>
         *
         * */



        final int minutes = Math.abs(event);

        final String type = calculator.isChargingEvent(event) ?
                "minutos carregando"
                : calculator.isGamingEvent(event) ? "minutos jogando"
                : "evento neutro";

        final String op = calculator.isChargingEvent(event) ? "+"
                : calculator.isGamingEvent(event) ? "-"
                : "";

        final String limitNote = (uncappedBatteryLevel > 100 && finalBatteryLevel == 100) ? " (limite máximo)" :
                (uncappedBatteryLevel < 0 && finalBatteryLevel == 0) ? " (limite mínimo)" : "";

        System.out.printf(
                "%d. Evento [%d] -> %d %s -> %d%% %s%d%% = %d%%%s\n",
                index + 2,
                index,
                minutes,
                type,
                previousBattery,
                op,
                Math.abs(change),
                finalBatteryLevel,
                limitNote
        );
    }

    /**
     *
     * Português
     *
     * Exibe um array de inteiros no formato [a, b, c].
     *
     * @param array Array a ser exibido.
     */

    /**
     *
     * English
     *
     * Displays an array of integers in the format [a, b, c].
     *
     * @param array Array to display.
     */

    public static void printFormattedArray(final int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }
}
