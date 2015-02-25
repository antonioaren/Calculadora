Feature: Resta de dos numeros enteros

  Scenario Outline: Resta
    Given El numero <primerNumero>
    When le restamos el numero <segundoNumero>
    Then el resultado es <resultado>
  Examples:
    | primerNumero | segundoNumero | resultado |
    |   0          |       1       |    -1     |
    |   3          |       2       |     1     |
    |   3          |      -4       |     7     |