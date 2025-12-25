Feature: Charge EV
  In order to charge my EV and pay correctly
  As a client
  I want the cost to depend on charger type, energy and charging duration

  Scenario: Client charges EV on AC charger and pays from account
    Given a client account with id 1, name "Omar Ftaaiti" and email "omar@example.com" and balance 50.0
    And a location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And a charger with id 100, type "AC" and status "AVAILABLE" at that location
    And a price for this location and charger type "AC" with price per kwh 0.25 and price per minute 0.10
    When the client starts a charging session on charger with id 100
    And the client charges 20.0 kwh for 60 minutes
    Then the total charging cost should be 11.0
    And the client account balance should be 39.0
