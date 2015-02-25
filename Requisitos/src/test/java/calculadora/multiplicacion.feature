Feature: Multiplicacion de dos numeros enteros

  Scenario Outline: Multiplicacion
    Given El numero <primerNumero>
    When lo multiplicamos por <segundoNumero>
    Then el resultado es <resultado>
    Examples:
      | primerNumero | segundoNumero | resultado |
      |     1        |       0       |    0      |
      |     2        |       3       |    6      |
      |     3        |      -4       |   -12     |
