package org.example;

import org.example.functions.calculator.BatteryChangeCalculator;
import org.example.functions.updater.BatteryLevelUpdater;
import org.example.functions.limits.BatteryLimitsApplier;
import org.example.utills.PrintUtils;

/**
 * Português
 *
 * Monitor de Bateria utilizando os princípios SOLID em uma única classe.
 *
 * Aplicação dos princípios SOLID:
 * - **SRP (Single Responsibility Principle)**: Cada função possui uma responsabilidade única e bem definida.
 * - **OCP (Open/Closed Principle)**: As funções são projetadas para serem estendidas sem a necessidade de modificar o código existente.
 * - **LSP (Liskov Substitution Principle)**: As funções podem ser substituídas por versões mais específicas sem afetar o comportamento esperado.
 * - **ISP (Interface Segregation Principle)**: Cada função define uma interface mínima e específica, evitando dependências desnecessárias.
 * - **DIP (Dependency Inversion Principle)**: As funções dependem de abstrações (parâmetros), e não de implementações concretas.
 *
 * 📁 **Diretório de Testes**:
 * Para testar esta classe corretamente, utilize o diretório e pacote correto exemplo seria!:
 * `package com.java.test;`
 *
 * 🔎 **Observação Adicional**:
 * Este projeto é independente, mas está relacionado a outro projeto complementar que desenvolvi:
 *
 * ---
 * 🔗 **LibreTranslate Java Client**
 * Um cliente Java moderno e completo para a API do LibreTranslate, com recursos avançados:
 *
 * - Traduções **Síncronas e Assíncronas** – Modos flexíveis para diferentes necessidades.
 * - **Limitação Inteligente de Requisições** – Ajuste automático da taxa de chamadas conforme as respostas da API.
 * - **Cache de Tradução Configurável** – Reduz chamadas para traduções recorrentes.
 * - **Monitoramento com Métricas Detalhadas** – Acompanhe o desempenho com estatísticas abrangentes.
 * - **Gerenciamento de Recursos** – Suporte total ao `AutoCloseable` para liberação automática.
 * - **Processamento de Comandos** – Traduz comandos individualmente com modos de operação flexíveis.
 * - **Arquitetura Modular** – Separação clara de responsabilidades com interfaces bem definidas.
 *
 * ✅ Compatível com: **JDK 8, JDK 11, JDK 17 e JDK 20**
 *
 * 📦 Repositórios:
 * - Projeto completo: https://github.com/Vidigal-code/libretranslate-java
 * - Versão otimizada e simplificada: https://github.com/Vidigal-code/libretranslate-simple-java
 */


/**
 * English
 *
 * Battery Monitor using SOLID principles in a single class.
 *
 * Application of SOLID principles:
 * - **SRP (Single Responsibility Principle)**: Each function has a single and well-defined responsibility.
 * - **OCP (Open/Closed Principle)**: Functions are designed to be extended without the need to modify existing code.
 * - **LSP (Liskov Substitution Principle)**: Functions can be replaced by more specific versions without affecting the expected behavior.
 * - **ISP (Interface Segregation Principle)**: Each function defines a minimal and specific interface, avoiding unnecessary dependencies.
 * - **DIP (Dependency Inversion Principle)**: Functions depend on abstractions (parameters), not on concrete implementations. *
 * 📁 **Test Directory**:
 * To test this class correctly, use the correct directory and package, for example:
 * `package com.java.test;`
 *
 * 🔎 **Additional Note**:
 * This project is independent, but is related to another complementary project I developed:
 *
 * ---
 * 🔗 **LibreTranslate Java Client**
 * A modern and complete Java client for the LibreTranslate API, with advanced features:
 *
 * - **Synchronous and Asynchronous** Translations – Flexible modes for different needs.
 * - **Smart Request Throttling** – Automatically adjusts the call rate according to the API responses.
 * - **Configurable Translation Cache** – Reduces calls for recurring translations.
 * - **Monitoring with Detailed Metrics** – Track performance with comprehensive statistics. * - **Resource Management** – Full support for `AutoCloseable` for automatic release.
 * - **Command Processing** – Translate commands individually with flexible operation modes.
 * - **Modular Architecture** – Clear separation of responsibilities with well-defined interfaces.
 *
 * ✅ Compatible with: **JDK 8, JDK 11, JDK 17 and JDK 20**
 *
 * 📦 Repositories:
 * - Full project: https://github.com/Vidigal-code/libretranslate-java
 * - Optimized and simplified version: https://github.com/Vidigal-code/libretranslate-simple-java
 */

public class BatteryMonitor {

    private final BatteryChangeCalculator changeCalculator;
    private final BatteryLevelUpdater levelUpdater;
    private final BatteryLimitsApplier limitsApplier;

    private static final int INITIAL_BATTERY_LEVEL = 50;

    public BatteryMonitor() {
        this.changeCalculator = new BatteryChangeCalculator();
        this.levelUpdater = new BatteryLevelUpdater();
        this.limitsApplier = new BatteryLimitsApplier();
    }

    /**
     *
     * Português
     *
     * Função principal que monitora a bateria processando eventos
     *
     * @param events array de eventos de carregamento/uso
     * @return porcentagem final da bateria (0-100)
     */

    /**
     *
     * English
     *
     * Main function that monitors the battery by processing events
     *
     * @param events array of charging/usage events
     * @return final battery percentage (0-100)
     */
    public int getBattery(int[] events) {
        int currentBatteryLevel = INITIAL_BATTERY_LEVEL;

        for (int event : events) {
            int batteryChange = changeCalculator.calculateBatteryChange(event);
            currentBatteryLevel = levelUpdater.updateBatteryLevel(currentBatteryLevel, batteryChange);
            currentBatteryLevel = limitsApplier.applyBatteryLimits(currentBatteryLevel);
        }

        return currentBatteryLevel;
    }

    /**
     *
     * Português
     *
     * Exibe passo a passo os eventos de bateria e suas consequências.
     *
     * @param events Array de eventos a serem demonstrados.
     */

    /**
     *
     * English
     *
     * Displays battery events and their consequences step by step.
     *
     * @param events Array of events to be demonstrated.
     */
    public void demonstrateProcess(int[] events) {
        int currentBatteryLevel = INITIAL_BATTERY_LEVEL;
        PrintUtils.printInitialBatteryStatus(currentBatteryLevel);

        for (int eventIndex = 0; eventIndex < events.length; eventIndex++) {
            int evento = events[eventIndex];
            int previousBatteryLevel = currentBatteryLevel;
            int change = changeCalculator.calculateBatteryChange(evento);

            currentBatteryLevel = levelUpdater.updateBatteryLevel(currentBatteryLevel, change);
            int newBatteryLevelUncapped = currentBatteryLevel;
            currentBatteryLevel = limitsApplier.applyBatteryLimits(currentBatteryLevel);

            PrintUtils.printEventDetails(
                    eventIndex,
                    evento,
                    previousBatteryLevel,
                    change,
                    newBatteryLevelUncapped,
                    currentBatteryLevel,
                    changeCalculator
            );
        }
    }


    /**
     * 
     * Português
     *
     * Executa um teste com nome descritivo e imprime o resultado final.
     *
     * @param testName Nome do teste.
     * @param events  Eventos a serem processados.
     */
    
    /**
     *
     * English
     *
     * Runs a test with a descriptive name and prints the final result.
     *
     * @param testName Name of the test.
     * @param events Events to be processed.
     */

    private void executeTest(String testName, int[] events) {
        System.out.print(" " + testName + " ");
        PrintUtils.printFormattedArray(events);
        System.out.println(": " + getBattery(events) + "% ");
    }

    /**
     *
     * Português
     *
     * Método principal para execução de testes e demonstrações.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */

    /**
     *
     * English
     *
     * Main method for running tests and demos.
     *
     * @param args Command line arguments (unused).
     */

    public static void main(String[] args) {
        BatteryMonitor monitor = new BatteryMonitor();

        int[] eventsExampleProblem = {10, -20, 61, -15};

        System.out.print("Entrada:\nn = " + eventsExampleProblem.length + "\neventos = ");
        PrintUtils.printFormattedArray(eventsExampleProblem);
        System.out.println("\n\nProcesso:");

        monitor.demonstrateProcess(eventsExampleProblem);

        int resultado = monitor.getBattery(eventsExampleProblem);
        System.out.println("\nSaída esperada:\n" + resultado);

        System.out.println("\n\nTESTES ADICIONAIS");
        monitor.executeTest("Teste original do código {10, -20, 61, -16}", new int[]{10, -20, 61, -16});
        monitor.executeTest("Carregamento", new int[]{5, 10});
        monitor.executeTest("Uso", new int[]{-30, -25});
        monitor.executeTest("Limite máximo (com evento 60)", new int[]{60});
        monitor.executeTest("Limite máximo (com evento 20, não atinge o limite)", new int[]{20});
        monitor.executeTest("Limite mínimo", new int[]{-60});
        monitor.executeTest("Eventos nulos ou vazios", new int[]{});
        monitor.executeTest("Evento zero", new int[]{0, 10, 0, -5});
        monitor.executeTest("Múltiplos carregamentos que excedem 100%", new int[]{30, 40, 50});
        monitor.executeTest("Múltiplos usos que chegam a 0%", new int[]{-20, -20, -20});
        monitor.executeTest("Alternância carregamento/uso", new int[]{20, -10, 30, -25, 15});
    }
}

/**
 *
 * Português
 *
 * Calcula o percentual final da bateria de um laptop após uma sequência de eventos de uso e carregamento.
 *
 * <p>Regras:
 * <ul>
 *   <li>Carga inicial da bateria é 50%.</li>
 *   <li>Evento positivo indica minutos carregando (+1% por minuto).</li>
 *   <li>Evento negativo indica minutos jogando (-1% por minuto).</li>
 *   <li>A bateria não pode ultrapassar 100% nem ficar abaixo de 0%.</li>
 * </ul>
 *
 * <p>Exemplo de execução:
 *
 * Entrada:
 * <pre>
 *  n = 4
 *  eventos = [10, -20, 61, -15]
 * </pre>
 *
 * Processo:
 * <ol>
 *   <li>Carga inicial: 50%</li>
 *   <li>Evento [0] -> 10 minutos carregando  -> 50% + 10% = 60%</li>
 *   <li>Evento [1] -> 20 minutos jogando     -> 60% - 20% = 40%</li>
 *   <li>Evento [2] -> 61 minutos carregando  -> 40% + 61% = 100% (limite máximo)</li>
 *   <li>Evento [3] -> 15 minutos jogando     -> 100% - 15% = 85%</li>
 * </ol>
 *
 * Saída esperada:
 * <pre>
 * 85
 * </pre>
 *
 * <p>Testes adicionais:
 * <ul>
 *   <li>[10, -20, 61, -16] -> 84%</li>
 *   <li>[5, 10] -> 65%</li>
 *   <li>[-30, -25] -> 0%</li>
 *   <li>[60] -> 100% (limite superior)</li>
 *   <li>[20] -> 70%</li>
 *   <li>[-60] -> 0% (limite inferior)</li>
 *   <li>[] -> 50% (nenhum evento)</li>
 *   <li>[0, 10, 0, -5] -> 55%</li>
 *   <li>[30, 40, 50] -> 100%</li>
 *   <li>[-20, -20, -20] -> 0%</li>
 *   <li>[20, -10, 30, -25, 15] -> 80%</li>
 * </ul>
 *
 * @param eventos array de inteiros representando os minutos carregando (+) ou jogando (-)
 * @return percentual final da bateria após processar todos os eventos
 */

/**
 *
 * English
 *
 * Calculates the final battery percentage of a laptop after a sequence of usage and charging events. *
 * <p>Rules:
 * <ul>
 * <li>Initial battery charge is 50%.</li>
 * <li>Positive event indicates minutes of charging (+1% per minute).</li>
 * <li>Negative event indicates minutes of playing (-1% per minute).</li>
 * <li>Battery cannot exceed 100% nor fall below 0%.</li>
 * </ul>
 *
 * <p>Runtime example:
 *
 * Input:
 * <pre>
 * n = 4
 * events = [10, -20, 61, -15]
 * </pre>
 *
 * Process:
 * <ol>
 * <li>Initial charge: 50%</li>
 * <li>Event [0] -> 10 minutes charging -> 50% + 10% = 60%</li>
 * <li>Event [1] -> 20 minutes playing -> 60% - 20% = 40%</li>
 * <li>Event [2] -> 61 minutes loading -> 40% + 61% = 100% (upper limit)</li>
 * <li>Event [3] -> 15 minutes playing -> 100% - 15% = 85%</li>
 * </ol>
 *
 * Expected output:
 * <pre>
 * 85
 * </pre>
 *
 * <p>Additional tests:
 * <ul>
 * <li>[10, -20, 61, -16] -> 84%</li>
 * <li>[5, 10] -> 65%</li>
 * <li>[-30, -25] -> 0%</li>
 * <li>[60] -> 100% (upper limit)</li>
 * <li>[20] -> 70%</li>
 * <li>[-60] -> 0% (lower limit)</li>
 * <li>[] -> 50% (no events)</li>
 * <li>[0, 10, 0, -5] -> 55%</li>
 * <li>[30, 40, 50] -> 100%</li>
 * <li>[-20, -20, -20] -> 0%</li>
 * <li>[20, -10, 30, -25, 15] -> 80%</li>
 * </ul>
 *
 * @param events array of integers representing minutes of charging (+) or playing (-)
 * @return final battery percentage after processing all events
 */