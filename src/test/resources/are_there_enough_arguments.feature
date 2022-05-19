Feature: Are there enough arguments?
  Minimum and maximum of three arguments are required to run the converter.

  Background:
    Given generate an empty list to arguments

  Scenario: Number of arguments is 3
    Given number of arguments is three
    When I ask whether three arguments are enough
    Then  I should be told Yes

  Scenario Outline: Number of arguments is not 3
    Given number of arguments is "<argument>"
    When I ask whether "<argument>" arguments are enough
    Then I should be told No

    Examples:
      | argument |
      | 2        |
      | 1        |
      | 0        |
      | 4        |