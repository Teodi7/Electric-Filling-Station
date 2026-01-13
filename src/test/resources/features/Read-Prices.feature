Feature: Read prices
  Prices can be viewed per location and charger type.

  Scenario: Read prices for a location
    Given a location with name "FH-Technikum" exists
    And a price for AC chargers at "FH-Technikum" is set to 0.30 EUR per kWh
    And a price for DC chargers at "FH-Technikum" is set to 0.45 EUR per kWh
    When the owner requests prices for location "FH-Technikum"
    Then the system should return 2 prices
