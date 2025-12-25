Feature: Read Prices
  As an Owner
  I want to read the prices for a specific location
  So that I can see the prices

  Scenario: Owner reads prices for a location
    Given there is a price location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And a price exists for location with id 1, charger type "AC", price per kwh 0.25 and price per minute 0.10
    And a price exists for location with id 1, charger type "DC", price per kwh 0.40 and price per minute 0.20
    When the owner requests the prices for location with id 1
    Then the system should return 2 prices for location with id 1

