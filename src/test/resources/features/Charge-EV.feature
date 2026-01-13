Feature: Charge electric vehicle
  A client charges an electric vehicle using a charger.

  Scenario: Client charges vehicle
    Given a location with name "FH-Technikum" exists
    And a charger with id 200 of type AC exists at "FH-Technikum"
    And a price for AC chargers at "FH-Technikum" is set to 0.30 EUR per kWh
    And a client with id 1, name "Omar Ftaiti", email "omar@example.com" exists
    And the client with id 1 tops up the balance by 50.00 EUR
    When the client charges 20.0 kWh at "FH-Technikum" using charger 200
    Then the client balance should be 44.00 EUR


        # edge case
  Scenario: Charging with a charger that is out of service should not create an invoice
    Given a location with name "FH-Technikum" exists
    And a charger with id 300 of type AC exists at "FH-Technikum"
    And a price for AC chargers at "FH-Technikum" is set to 0.30 EUR per kWh
    And a client with id 1, name "Omar", email "omar@example.com" exists
    And the charger with id 300 is out of service
    When the client charges 10.0 kWh at "FH-Technikum" using charger 300
    Then no invoice should exist
