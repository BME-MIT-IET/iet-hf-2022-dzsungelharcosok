Feature: Are there enough arguments?
  Minimum of two arguments are required to run the converter

  Background:
    Given a list to arguments

  Scenario Outline: Number of arguments is or is not enough
    Given number of arguments is "<argument>"
    When I ask are there enough arguments
    Then  Then I should be told "<answer>"

    Examples:
      | argument | answer |
      | 3        | Yes    |
      | 2        | No     |
      | 1        | No     |
      | 0        | No     |
      | 4        | No     |