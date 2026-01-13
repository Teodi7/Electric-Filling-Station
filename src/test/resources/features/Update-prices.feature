Feature: Update prices
  Prices can change over time and are updated by the owner.

  Scenario: Update an existing price
    Given a location with name "FH-Technikum" exists
    And a price for AC chargers at "FH-Technikum" is set to 0.30 EUR per kWh
    When the owner updates the price for AC chargers at "FH-Technikum" to 0.35 EUR per kWh
    Then the latest price for AC chargers at "FH-Technikum" should be 0.35 EUR per kWh
