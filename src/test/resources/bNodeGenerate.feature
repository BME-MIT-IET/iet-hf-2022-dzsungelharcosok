Feature: Generate BNode
  We want to know if the Node Generator only works when the rowIndex is not -1

  Scenario Outline: generator rowIndex input is not -1
    Given a string array and set the rowIndex to <argument>
    When  I ask whether the function return is null
    Then  I should be told the return value is not null

    Examples:
    | argument |
    | 1        |
    | 3        |
    | 6        |

  Scenario: generator rowIndex input is -1
    Given a string array and set the rowIndex to -1
    When  I ask whether the function return is null
    Then  I should be told the return value is null