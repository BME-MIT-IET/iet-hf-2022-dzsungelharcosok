Feature: getIndex work with chars
  we want to know if the getIndex works well with characters as well as numbers, because of the header row

  Background:
    Given generate test environment

  Scenario: function input is a number
    Given a number as a string and an initialized list of columns
    When  I ask whether the function is working with number
    Then  I should be told the it is working

  Scenario: function input is a string
    Given a string and an initialized list of columns
    When  I ask whether the function is working with string
    Then  I should be told the it is working