package org.example.functions.calculator;

/**
 *
 * Português
 *
 * Classe utilitária para calcular a variação da bateria com base em eventos de carregamento ou uso em jogos.
 */


/**
 *
 * English
 *
 * Utility class to calculate battery variation based on charging events or game usage.
 */
public class BatteryChangeCalculator {

    /**
     * Português
     *
     * Taxa de carregamento da bateria por minuto. */

    /**
     * English
     *
     * Battery charging rate per minute. */


    private static final int CHARGING_RATE_PER_MINUTE = 1;

    /**
     * Português
     *
     * Taxa de consumo da bateria por minuto durante jogos. */

    /**
     * English
     *
     * Battery consumption rate per minute during games. */



    private static final int CONSUMPTION_RATE_PER_MINUTE = 1;

    /**
     *
     * Português
     *
     * Calcula a mudança na porcentagem da bateria com base no evento informado.
     *
     * @param event duração do evento; valor positivo para carregamento e negativo para jogos
     * @return a mudança na porcentagem da bateria
     */


    /**
     *
     *
     * English
     *
     * Calculates the change in battery percentage based on the given event.
     *
     * @param event duration of the event; positive value for charging and negative for gaming
     * @return the change in battery percentage
     */

    public int calculateBatteryChange(int event) {
        return isChargingEvent(event) ? calculateChargingIncrease(event)
                : isGamingEvent(event) ? calculateGamingDecrease(event)
                : 0;
    }


    /**
     *
     * Português
     *
     * Verifica se o evento é de carregamento.
     *
     * @param event duração do evento
     * @return true se for um evento de carregamento (valor positivo), false caso contrário
     */

    /**
     *
     * English
     *
     * Checks if the event is a loading event.
     *
     * @param event duration of the event
     * @return true if it is a loading event (positive value), false otherwise
     */

    public boolean isChargingEvent(int event) {
        return event > 0;
    }

    /**
     *
     * Português
     *
     * Verifica se o evento é de uso em jogos.
     *
     * @param evento duração do evento
     * @return true se for um evento de jogos (valor negativo), false caso contrário
     */

    /**
     *
     * English
     *
     * Checks if the event is for use in games.
     *
     * @param event duration of the event
     * @return true if it is a game event (negative value), false otherwise
     */

    public boolean isGamingEvent(int event) {
        return event < 0;
    }

    /**
     *
     * Português
     *
     * Calcula o aumento da porcentagem da bateria com base no tempo de carregamento.
     *
     * @param chargingMinutes duração do carregamento em minutos
     * @return o aumento da porcentagem da bateria
     */


    /**
     *
     * English
     *
     * Calculates the battery percentage increase based on the charging time.
     *
     * @param chargingMinutes charging duration in minutes
     * @return the battery percentage increase
     */

    public int calculateChargingIncrease(int chargingMinutes) {
        return chargingMinutes * CHARGING_RATE_PER_MINUTE;
    }

    /**
     *
     * Português
     *
     * Calcula a diminuição da porcentagem da bateria com base no tempo jogando.
     *
     * @param gamingMinutes duração do uso em jogos em minutos (deve ser negativo)
     * @return a diminuição da porcentagem da bateria
     */


    /**
     *
     * English
     *
     * Calculates battery percentage decrease based on gaming time.
     *
     * @param gamingMinutes duration of gaming usage in minutes (must be negative)
     * @return battery percentage decrease
     */
    public int calculateGamingDecrease(int gamingMinutes) {
        return gamingMinutes * CONSUMPTION_RATE_PER_MINUTE;
    }
}
