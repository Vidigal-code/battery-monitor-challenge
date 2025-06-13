# ✅ Análise do Sistema de Monitoramento de Bateria / Battery Monitoring System Analysis

<details>
<summary>Português - MD</summary>

Este documento analisa o design e a implementação de um sistema Java para monitorar o nível de bateria de um laptop,
conforme um desafio proposto. O desafio envolve calcular a porcentagem final da bateria após uma sequência de eventos de
uso (jogos) e carregamento, seguindo regras específicas. A solução apresentada adere aos princípios SOLID para promover
modularidade, testabilidade e manutenibilidade.

---

### 🎯 O Desafio Original

O problema consiste em desenvolver uma função (`getBattery`) que simule o comportamento da bateria de um laptop com base
nos seguintes critérios:

* **Entrada:** Uma lista de `n` números inteiros (`eventos`), onde cada elemento representa um evento.
* **Estado Inicial:** A bateria começa com **50%**.
* **Regras dos Eventos:**
    * Um **valor positivo** `x` no array `eventos` indica `x` minutos **carregando** o laptop. Cada minuto carregando
      aumenta a bateria em 1%.
    * Um **valor negativo** `y` no array `eventos` indica `abs(y)` minutos **jogando** (consumindo bateria). Cada minuto
      jogando diminui a bateria em 1%.
* **Limites da Bateria:**
    * A bateria **não pode ultrapassar 100%**.
    * A bateria **não pode ficar abaixo de 0%**.
* **Saída:** A função deve retornar a **porcentagem final da bateria** como um número inteiro após processar todos os
  eventos.

**Exemplo do Desafio:**

* `n = 4`
* `eventos = [10, -20, 61, -15]`
* **Processo Esperado:**
    1. Carga inicial: 50%
    2. Evento [0] -> 10 min carregando -> 50% + 10% = 60%
    3. Evento [1] -> 20 min jogando -> 60% - 20% = 40%
    4. Evento [2] -> 61 min carregando -> 40% + 61% = 101% -> **100%** (limite máximo)
    5. Evento [3] -> 15 min jogando -> 100% - 15% = **85%**
* **Saída Esperada:** `85`

---

### 💡 Como a Solução Java Aborda o Desafio

A solução Java implementa a função `getBattery(int[] eventos)` na classe `org.example.BatteryMonitor`. Esta função
processa a lista de eventos da seguinte maneira, atendendo a todos os requisitos do desafio:

1. **Estado Inicial:** A bateria é inicializada em `50%` (definido pela constante `INITIAL_BATTERY_LEVEL` na classe
   `BatteryMonitor`).
2. **Processamento de Eventos:** Para cada `evento` na lista de entrada:
    * A classe `org.example.functions.calculator.BatteryChangeCalculator` é usada para determinar a variação percentual
      da bateria.
        * Se o evento é positivo (carregamento), a variação é `+evento %`.
        * Se o evento é negativo (jogos), a variação é `evento %` (resultando em uma diminuição, pois o valor do evento
          já é negativo).
        * Isso implementa a regra de "+1% por minuto de carga" e "-1% por minuto de jogo".
    * A classe `org.example.functions.updater.BatteryLevelUpdater` soma essa variação calculada ao nível atual da
      bateria.
    * A classe `org.example.functions.limits.BatteryLimitsApplier` então aplica os limites, garantindo que o novo nível
      da bateria não ultrapasse `100%` nem fique abaixo de `0%`.
3. **Saída:** Após todos os eventos serem processados, o nível final da bateria é retornado como um inteiro, conforme
   solicitado.

---

### 🏛️ Arquitetura e Princípios de Design (SOLID)

O sistema é projetado com uma clara separação de responsabilidades, aderindo aos princípios SOLID:

* **SRP (Single Responsibility Principle):** Cada classe tem uma única e bem definida responsabilidade.
    * `BatteryChangeCalculator`: Calcula a variação da bateria com base em um evento (implementa a regra de +/- 1% por
      minuto).
    * `BatteryLimitsApplier`: Aplica os limites de 0% e 100% à bateria (implementa a regra dos limites).
    * `BatteryLevelUpdater`: Atualiza o nível da bateria com a variação (operação aritmética simples).
    * `PrintUtils`: Formata e exibe informações no console (auxiliar para demonstração).
    * `BatteryMonitor`: Orquestra las operações, implementa a lógica principal do `getBattery` e gerencia o fluxo de
      dados (incluindo a carga inicial).
* **OCP (Open/Closed Principle):** As classes são projetadas para serem extensíveis sem modificar seu código existente.
  Por exemplo, `BatteryChangeCalculator` poderia ser estendido para suportar novos tipos de eventos (ex: `standby`,
  `uso de app específico`) através de herança ou composição (Strategy Pattern) sem alterar a lógica de `BatteryMonitor`
  se este dependesse de uma abstração.
* **LSP (Liskov Substitution Principle):** Embora não haja herança explícita entre os componentes funcionais principais,
  o design permite que cada componente possa ser substituído por outra implementação que cumpra o mesmo contrato, sem
  quebrar o sistema.
* **ISP (Interface Segregation Principle):** Cada classe expõe uma interface mínima e coesa através de seus métodos
  públicos, evitando que clientes dependam de métodos que não usam.
* **DIP (Dependency Inversion Principle):** A classe `BatteryMonitor` depende das funcionalidades das outras classes (
  `BatteryChangeCalculator`, `BatteryLevelUpdater`, `BatteryLimitsApplier`) que são instanciadas em seu construtor. Isso
  promove a coesão e permite que `BatteryMonitor` foque na orquestração.

---

### 🧩 Análise Detalhada das Classes (em relação ao Desafio)

#### 1. `org.example.functions.calculator.BatteryChangeCalculator`

* **Responsabilidade no Desafio:** Implementar a regra de conversão de minutos de evento para variação percentual da
  bateria.
    * Eventos positivos (carregamento): `minutos * CHARGING_RATE_PER_MINUTE` (onde a taxa é 1).
    * Eventos negativos (jogos): `minutos * CONSUMPTION_RATE_PER_MINUTE` (onde a taxa é 1, e 'minutos' já é negativo).
* **Métodos Chave:** `calculateBatteryChange(int evento)`.

#### 2. `org.example.functions.limits.BatteryLimitsApplier`

* **Responsabilidade no Desafio:** Aplicar os limites de 0% (mínimo) e 100% (máximo) ao nível da bateria após cada
  atualização.
* **Constantes:** `MIN_BATTERY = 0`, `MAX_BATTERY = 100`.
* **Método Chave:** `applyBatteryLimits(int batteryLevel)`.

#### 3. `org.example.functions.updater.BatteryLevelUpdater`

* **Responsabilidade no Desafio:** Realizar a operação aritmética de adicionar a variação calculada ao nível atual da
  bateria.
* **Método Chave:** `updateBatteryLevel(int currentLevel, int delta)`.

#### 4. `org.example.utills.PrintUtils`

* **Responsabilidade no Desafio:** Nenhuma direta na lógica de cálculo, mas crucial para a demonstração e verificação do
  processo passo a passo, conforme o exemplo do desafio.
* **Métodos Chave:** `printInitialBatteryStatus()`, `printEventDetails()`.

#### 5. `org.example.BatteryMonitor`

* **Responsabilidade no Desafio:** Orquestrar todo o processo para a função `getBattery`.
    * Define a `INITIAL_BATTERY_LEVEL = 50`.
    * Itera sobre os `eventos`.
    * Utiliza `BatteryChangeCalculator`, `BatteryLevelUpdater`, e `BatteryLimitsApplier` na ordem correta para cada
      evento.
    * Retorna o resultado final.
* **Métodos Chave:** `getBattery(int[] eventos)`, `demonstrarProcesso(int[] eventos)` (para visualização),
  `main(String[] args)` (para execução e testes).

---

### 📋 Demonstração do Exemplo Principal do Desafio

Vamos analisar como a solução processa o exemplo `eventos = [10, -20, 61, -15]`:

1. **`BatteryMonitor.getBattery([10, -20, 61, -15])` é chamado.**
2. `currentBatteryLevel` é inicializado com `INITIAL_BATTERY_LEVEL` = **50%**.
3. **Loop de Eventos:**
    * **Evento `10` (carregando):**
        * `changeCalculator.calculateBatteryChange(10)` retorna `+10`.
        * `levelUpdater.updateBatteryLevel(50, 10)` retorna `60`.
        * `limitsApplier.applyBatteryLimits(60)` retorna `60`.
        * `currentBatteryLevel` agora é **60%**.
    * **Evento `-20` (jogando):**
        * `changeCalculator.calculateBatteryChange(-20)` retorna `-20`.
        * `levelUpdater.updateBatteryLevel(60, -20)` retorna `40`.
        * `limitsApplier.applyBatteryLimits(40)` retorna `40`.
        * `currentBatteryLevel` agora é **40%**.
    * **Evento `61` (carregando):**
        * `changeCalculator.calculateBatteryChange(61)` retorna `+61`.
        * `levelUpdater.updateBatteryLevel(40, 61)` retorna `101`.
        * `limitsApplier.applyBatteryLimits(101)` retorna `100` (limite máximo aplicado).
        * `currentBatteryLevel` agora é **100%**.
    * **Evento `-15` (jogando):**
        * `changeCalculator.calculateBatteryChange(-15)` retorna `-15`.
        * `levelUpdater.updateBatteryLevel(100, -15)` retorna `85`.
        * `limitsApplier.applyBatteryLimits(85)` retorna `85`.
        * `currentBatteryLevel` agora é **85%**.
4. **Fim do Loop.** A função retorna `currentBatteryLevel`, que é **85**.

Isso corresponde exatamente ao resultado esperado pelo desafio.

---

### 🧪 Estratégia de Testes

A corretude da solução é verificada através de:

1. **Demonstração Visual (`main` e `demonstrarProcesso`):**
   O método `main` em `BatteryMonitor` executa o exemplo principal do desafio e vários outros cenários de teste. A saída
   detalhada gerada por `demonstrarProcesso` (utilizando `PrintUtils`) permite uma verificação visual passo a passo,
   confirmando que a lógica interna segue as regras do desafio.

2. **Testes Unitários (JUnit):**
   As classes `BatteryMonitorTest.java` e `BatteryMonitorTestAdditional.java` contêm testes unitários automatizados que
   cobrem uma variedade de cenários para a função `getBattery`. Estes incluem:
    * O exemplo principal do problema.
    * Casos sem eventos.
    * Eventos apenas de carregamento ou apenas de uso.
    * Cenários que atingem os limites superior (100%) e inferior (0%).
    * Eventos com valor zero.
    * Sequências alternadas de carregamento e uso.
    * Múltiplos eventos que, acumulados, testam os limites.

   Esses testes garantem a robustez da função `getBattery` e ajudam a prevenir regressões caso o código seja modificado.

   **Exemplo de Teste Unitário (`BatteryMonitorTest.java`):**

   ```java
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

       // ... outros métodos de teste ...
   }
   ```

   **Exemplo de Teste Unitário (`BatteryMonitorTestAdditional.java`):**

   ```java
   package org.example;

   import static org.junit.jupiter.api.Assertions.assertEquals;

   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.Test;

   public class BatteryMonitorTestAdditional { // Nome da classe corrigido para corresponder ao exemplo

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

       // ... outros métodos de teste ...
   }
   ```

---

### ✅ Conclusão

A solução Java apresentada para o sistema de monitoramento de bateria cumpre eficazmente todos os requisitos do desafio
proposto. A arquitetura modular, baseada nos princípios SOLID, e a abrangente estratégia de testes (tanto visuais quanto
unitários automatizados) garantem a corretude, manutenibilidade e robustez do código. O sistema calcula com precisão o
nível final da bateria, respeitando a carga inicial, as taxas de variação por evento e os limites de 0% e 100%.

---

### 🔗 Projeto Relacionado: LibreTranslate Java Client

*(Esta seção é mantida conforme o original, pois é uma informação adicional fornecida pelo autor)*

Este projeto de monitoramento de bateria é independente, mas o autor também desenvolveu um cliente Java moderno e
completo para a API do LibreTranslate.

**LibreTranslate Java Client** oferece recursos avançados como:

* Traduções **Síncronas e Assíncronas**.
* **Limitação Inteligente de Requisições**.
* **Cache de Tradução Configurável**.
* **Monitoramento com Métricas Detalhadas**.
* **Gerenciamento de Recursos** (`AutoCloseable`).
* **Processamento de Comandos**.
* **Arquitetura Modular**.

✅ **Compatível com:** JDK 8, JDK 11, JDK 17 e JDK 20

📦 **Repositórios:**

* Projeto
  completo: [https://github.com/Vidigal-code/libretranslate-java](https://github.com/Vidigal-code/libretranslate-java)
* Versão otimizada e
  simplificada: [https://github.com/Vidigal-code/libretranslate-simple-java](https://github.com/Vidigal-code/libretranslate-simple-java)

</details>

<details>
<summary>English - MD</summary>

This document analyzes the design and implementation of a Java system for monitoring a laptop's battery level,
as per a proposed challenge. The challenge involves calculating the final battery percentage after a sequence of
usage (gaming) and charging events, following specific rules. The presented solution adheres to SOLID principles to
promote
modularity, testability, and maintainability.

---

### 🎯 The Original Challenge 

The problem consists of developing a function (`getBattery`) that simulates the behavior of a laptop's battery based
on the following criteria:

* **Input:** A list of `n` integer numbers (`events`), where each element represents an event.
* **Initial State:** The battery starts at **50%**.
* **Event Rules:**
    * A **positive value** `x` in the `events` array indicates `x` minutes of **charging** the laptop. Each minute of
      charging
      increases the battery by 1%.
    * A **negative value** `y` in the `events` array indicates `abs(y)` minutes of **gaming** (consuming battery). Each
      minute
      of gaming decreases the battery by 1%.
* **Battery Limits:**
    * The battery **cannot exceed 100%**.
    * The battery **cannot go below 0%**.
* **Output:** The function must return the **final battery percentage** as an integer after processing all
  events.

**Challenge Example:**

* `n = 4`
* `events = [10, -20, 61, -15]`
* **Expected Process:**
    1. Initial charge: 50%
    2. Event [0] -> 10 min charging -> 50% + 10% = 60%
    3. Event [1] -> 20 min gaming -> 60% - 20% = 40%
    4. Event [2] -> 61 min charging -> 40% + 61% = 101% -> **100%** (maximum limit)
    5. Event [3] -> 15 min gaming -> 100% - 15% = **85%**
* **Expected Output:** `85`

---

### 💡 How the Java Solution Addresses the Challenge

The Java solution implements the `getBattery(int[] eventos)` function in the `org.example.BatteryMonitor` class. This
function
processes the list of events as follows, meeting all challenge requirements:

1. **Initial State:** The battery is initialized to `50%` (defined by the `INITIAL_BATTERY_LEVEL` constant in the
   `BatteryMonitor` class).
2. **Event Processing:** For each `evento` in the input list:
    * The `org.example.functions.calculator.BatteryChangeCalculator` class is used to determine the percentage change
      in the battery.
        * If the event is positive (charging), the change is `+event %`.
        * If the event is negative (gaming), the change is `event %` (resulting in a decrease, as the event value
          is already negative).
        * This implements the "+1% per minute of charge" and "-1% per minute of game" rule.
    * The `org.example.functions.updater.BatteryLevelUpdater` class adds this calculated change to the current battery
      level.
    * The `org.example.functions.limits.BatteryLimitsApplier` class then applies the limits, ensuring the new battery
      level does not exceed `100%` or fall below `0%`.
3. **Output:** After all events are processed, the final battery level is returned as an integer, as
   requested.

---

### 🏛️ Architecture and Design Principles (SOLID)

The system is designed with a clear separation of responsibilities, adhering to SOLID principles:

* **SRP (Single Responsibility Principle):** Each class has a single, well-defined responsibility.
    * `BatteryChangeCalculator`: Calculates the battery change based on an event (implements the +/- 1% per
      minute rule).
    * `BatteryLimitsApplier`: Applies the 0% and 100% limits to the battery (implements the limits rule).
    * `BatteryLevelUpdater`: Updates the battery level with the change (simple arithmetic operation).
    * `PrintUtils`: Formats and displays information on the console (auxiliary for demonstration).
    * `BatteryMonitor`: Orchestrates operations, implements the main logic of `getBattery`, and manages data flow
      (including initial charge).
* **OCP (Open/Closed Principle):** Classes are designed to be extensible without modifying their existing code.
  For example, `BatteryChangeCalculator` could be extended to support new event types (e.g., `standby`,
  `specific app usage`) through inheritance or composition (Strategy Pattern) without altering `BatteryMonitor`'s
  logic if it depended on an abstraction.
* **LSP (Liskov Substitution Principle):** Although there is no explicit inheritance among the main functional
  components,
  the design allows each component to be replaced by another implementation that fulfills the same contract, without
  breaking the system.
* **ISP (Interface Segregation Principle):** Each class exposes a minimal and cohesive interface through its public
  methods, preventing clients from depending on methods they do not use.
* **DIP (Dependency Inversion Principle):** The `BatteryMonitor` class depends on the functionalities of other classes (
  `BatteryChangeCalculator`, `BatteryLevelUpdater`, `BatteryLimitsApplier`) which are instantiated in its constructor.
  This
  promotes cohesion and allows `BatteryMonitor` to focus on orchestration.

---

### 🧩 Detailed Class Analysis (in relation to the Challenge)

#### 1. `org.example.functions.calculator.BatteryChangeCalculator`

* **Responsibility in the Challenge:** Implement the rule for converting event minutes to battery percentage change.
    * Positive events (charging): `minutes * CHARGING_RATE_PER_MINUTE` (where the rate is 1).
    * Negative events (gaming): `minutes * CONSUMPTION_RATE_PER_MINUTE` (where the rate is 1, and 'minutes' is already
      negative).
* **Key Methods:** `calculateBatteryChange(int evento)`.

#### 2. `org.example.functions.limits.BatteryLimitsApplier`

* **Responsibility in the Challenge:** Apply the 0% (minimum) and 100% (maximum) limits to the battery level after each
  update.
* **Constants:** `MIN_BATTERY = 0`, `MAX_BATTERY = 100`.
* **Key Method:** `applyBatteryLimits(int batteryLevel)`.

#### 3. `org.example.functions.updater.BatteryLevelUpdater`

* **Responsibility in the Challenge:** Perform the arithmetic operation of adding the calculated change to the current
  battery level.
* **Key Method:** `updateBatteryLevel(int currentLevel, int delta)`.

#### 4. `org.example.utills.PrintUtils`

* **Responsibility in the Challenge:** None direct to the calculation logic, but crucial for demonstrating and verifying
  the
  step-by-step process, as per the challenge example.
* **Key Methods:** `printInitialBatteryStatus()`, `printEventDetails()`.

#### 5. `org.example.BatteryMonitor`

* **Responsibility in the Challenge:** Orchestrate the entire process for the `getBattery` function.
    * Defines `INITIAL_BATTERY_LEVEL = 50`.
    * Iterates over the `eventos`.
    * Uses `BatteryChangeCalculator`, `BatteryLevelUpdater`, and `BatteryLimitsApplier` in the correct order for each
      event.
    * Returns the final result.
* **Key Methods:** `getBattery(int[] eventos)`, `demonstrarProcesso(int[] eventos)` (for visualization),
  `main(String[] args)` (for execution and tests).

---

### 📋 Demonstration of the Main Challenge Example

Let's analyze how the solution processes the example `eventos = [10, -20, 61, -15]`:

1. **`BatteryMonitor.getBattery([10, -20, 61, -15])` is called.**
2. `currentBatteryLevel` is initialized with `INITIAL_BATTERY_LEVEL` = **50%**.
3. **Event Loop:**
    * **Event `10` (charging):**
        * `changeCalculator.calculateBatteryChange(10)` returns `+10`.
        * `levelUpdater.updateBatteryLevel(50, 10)` returns `60`.
        * `limitsApplier.applyBatteryLimits(60)` returns `60`.
        * `currentBatteryLevel` is now **60%**.
    * **Event `-20` (gaming):**
        * `changeCalculator.calculateBatteryChange(-20)` returns `-20`.
        * `levelUpdater.updateBatteryLevel(60, -20)` returns `40`.
        * `limitsApplier.applyBatteryLimits(40)` returns `40`.
        * `currentBatteryLevel` is now **40%**.
    * **Event `61` (charging):**
        * `changeCalculator.calculateBatteryChange(61)` returns `+61`.
        * `levelUpdater.updateBatteryLevel(40, 61)` returns `101`.
        * `limitsApplier.applyBatteryLimits(101)` returns `100` (maximum limit applied).
        * `currentBatteryLevel` is now **100%**.
    * **Event `-15` (gaming):**
        * `changeCalculator.calculateBatteryChange(-15)` returns `-15`.
        * `levelUpdater.updateBatteryLevel(100, -15)` returns `85`.
        * `limitsApplier.applyBatteryLimits(85)` returns `85`.
        * `currentBatteryLevel` is now **85%**.
4. **End of Loop.** The function returns `currentBatteryLevel`, which is **85**.

This exactly matches the expected result from the challenge.

---

### 🧪 Testing Strategy

The correctness of the solution is verified through:

1. **Visual Demonstration (`main` and `demonstrarProcesso`):**
   The `main` method in `BatteryMonitor` executes the main challenge example and several other test scenarios. The
   detailed
   output generated by `demonstrarProcesso` (using `PrintUtils`) allows for a step-by-step visual verification,
   confirming that the internal logic follows the challenge rules.

2. **Unit Tests (JUnit):**
   The `BatteryMonitorTest.java` and `BatteryMonitorTestAdditional.java` classes contain automated unit tests that
   cover a variety of scenarios for the `getBattery` function. These include:
    * The main problem example.
    * Cases with no events.
    * Events of only charging or only usage.
    * Scenarios hitting the upper (100%) and lower (0%) limits.
    * Events with zero value.
    * Alternating sequences of charging and usage.
    * Multiple events that, when accumulated, test the limits.

   These tests ensure the robustness of the `getBattery` function and help prevent regressions if the code is modified.

   **Unit Test Example (`BatteryMonitorTest.java`):**

   ```java
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

       // ... other test methods ...
   }
   ```

   **Unit Test Example (`BatteryMonitorTestAdditional.java`):**

   ```java
   package org.example;

   import static org.junit.jupiter.api.Assertions.assertEquals;

   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.Test;

   public class BatteryMonitorTestAdditional { // Class name corrected to match example

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

       // ... other test methods ...
   }
   ```

---

### ✅ Conclusion

The Java solution presented for the battery monitoring system effectively meets all requirements of the proposed
challenge.
The modular architecture, based on SOLID principles, and the comprehensive testing strategy (both visual and
automated unit tests) ensure the correctness, maintainability, and robustness of the code. The system accurately
calculates
the final battery level, respecting the initial charge, event-based variation rates, and the 0% and 100% limits.

---

### 🔗 Related Project: LibreTranslate Java Client

*(This section is maintained as per the original, as it is additional information provided by the author)*

This battery monitoring project is independent, but the author has also developed a modern and
complete Java client for the LibreTranslate API.

**LibreTranslate Java Client** offers advanced features such as:

* **Synchronous and Asynchronous** Translations.
* **Smart Request Throttling**.
* **Configurable Translation Cache**.
* **Monitoring with Detailed Metrics**.
* **Resource Management** (`AutoCloseable`).
* **Command Processing**.
* **Modular Architecture**.

✅ **Compatible with:** JDK 8, JDK 11, JDK 17, and JDK 20

📦 **Repositories:**

* Complete
  project: [https://github.com/Vidigal-code/libretranslate-java](https://github.com/Vidigal-code/libretranslate-java)
* Optimized and
  simplified
  version: [https://github.com/Vidigal-code/libretranslate-simple-java](https://github.com/Vidigal-code/libretranslate-simple-java)

</details>

# 📊 Saída da Execução do Programa | Program Execution Output

### 📌 Português  
A imagem abaixo mostra a saída do programa **BatteryMonitor** com eventos de carga e descarga da bateria.  
A execução imprime o resultado da bateria após cada conjunto de eventos.

### 📌 English  
The image below shows the output of the **BatteryMonitor** program with charging and discharging events.  
The program prints the battery level result after each set of events.

**BatteryMonitor**

![BatteryMonitor](https://github.com/Vidigal-code/battery-monitor-challenge/blob/main/example/BatteryMonitorOutput.png?raw=true)

---

# ✅ Execução dos Testes JUnit | JUnit Test Execution

### 📌 Português  
Nesta imagem, vemos os testes **JUnit** sendo executados com sucesso.  
Todos os testes passaram usando o **JDK 21**, indicando que a lógica da bateria está funcionando corretamente para todos os casos testados.

### 📌 English  
In this image, we see the **JUnit** tests running successfully.  
All tests passed using **JDK 21**, indicating that the battery logic is working correctly for all tested scenarios.


**BatteryMonitorTest**

![BatteryMonitorTest](https://github.com/Vidigal-code/battery-monitor-challenge/blob/main/example/BatteryMonitorTest.png?raw=true)


**BatteryMonitorTestAdditional**

![BatteryMonitorTest](https://github.com/Vidigal-code/battery-monitor-challenge/blob/main/example/BatteryMonitorTestAdditional.png?raw=true)






