Feature: Client invoice
  After charging, the client receives a detailed invoice.

  Scenario: Invoice after charging
    Given a location with name "FH-Technikum" exists
    And a charger with id 200 of type AC exists at "FH-Technikum"
    And a price for AC chargers at "FH-Technikum" is set to 0.30 EUR per kWh
    And a client with id 1, name "Omar Ftaiti", email "omar@example.com" exists
    And the client with id 1 tops up the balance by 50.00 EUR
    When the client charges 20.0 kWh at "FH-Technikum" using charger 200
    Then an invoice should exist with:
      | location     | energy | pricePerKwh | total |
      | FH-Technikum | 20.0   | 0.30        | 6.00  |

      # edge case
  Scenario: Invoice should not exist if the client did not charge
    Then no invoice should exist
