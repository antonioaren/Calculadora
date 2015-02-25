Feature: Suma de dos numeros enteros

  Scenario Outline: Suma
    Given El numero <primerNumero>
    When le sumamos el numero <segundoNumero>
    Then el resultado es <resultado>
  Examples:
    | primerNumero | segundoNumero | resultado |
    |   3          |    4          |    7      |
    |   3          |   -3          |    0      |
