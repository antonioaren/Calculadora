Feature: Division de dos numeros enteros

  Scenario Outline: Division de dos numeros
    Given El numero <primerNumero>
    When lo dividimos por <segundoNumero>
    Then el resultado es <resultado>
  Examples:
    | primerNumero | segundoNumero | resultado |
    |   12         |    4          |    3      |
    |   12         |   -4          |   -3      |
    |   0          |    4          |    0      |

  Scenario: Division por cero
    Given El numero 4
    When lo dividimos por 0
    Then el resultado es un error
