Feature: Update Prices
  As an Owner
  I want to update the prices for a location
  So that I can change the cost settings

  Scenario: Owner updates the prices for a location
    Given there is a price location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And a price exists for location with id 1, charger type "AC", price per kwh 0.25 and price per minute 0.10
    When the owner updates the price for location with id 1, charger type "AC" to price per kwh 0.20 and price per minute 0.15
    Then the system should show price per kwh 0.20 and price per minute 0.15 for location with id 1 and charger type "AC"
