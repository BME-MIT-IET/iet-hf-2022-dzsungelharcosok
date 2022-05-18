Feature: Is it converted to rdf output
    The program must converted to rdf output

  Scenario: ttl is ttl
    Given result is ttl
    When the user start the converter
    Then the converted file extension should "ttl"

  Scenario: csv is not ttl
    Given result is csv
    When the user start the converter
    Then the converted file extension should "csv"
