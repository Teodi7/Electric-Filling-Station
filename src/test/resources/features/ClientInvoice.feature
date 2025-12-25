Feature: Client reviews detailed invoice items
  As a client,
  I want to see a detailed list of invoice items,
  so that I can track each charging session’s start time, duration, energy consumed,
  top-ups, my remaining balance and active rates.

  Scenario: Client sees detailed invoice items for all charging sessions and top-ups
    Given there is a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And the location with id 1 has an AC charger with id 101, type "AC" and status "AVAILABLE"
    And a price exists for location with id 1, charger type "AC", price per kwh 0.5 and price per minute 0.1
    And a client account with id 1 and an initial balance of 50.0
    And the client completes a charging session of 10.0 kWh and 30 minutes on charger with id 101 at location 1
    And the client tops up their balance by 20.0
    And the client completes another charging session of 20.0 kWh and 60 minutes on charger with id 101 at location 1
    When the client requests their detailed invoice items
    Then the system should return an invoice item for each charging session including start time, duration, energy consumed and applied rates
    And the system should return an invoice item for the top-up with the credited amount
    And each invoice item should include the remaining balance after the operation
