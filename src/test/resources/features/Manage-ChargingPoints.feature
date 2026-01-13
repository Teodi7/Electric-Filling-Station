Feature: Manage charger
  Chargers belong to a location and have a technical status.

  Scenario: Create charger for a location
    Given a location with name "FH-Technikum" exists
    When the owner adds a charger with id 100 of type AC to location "FH-Technikum"
    And the owner adds a charger with id 101 of type DC to location "FH-Technikum"
    Then the location "FH-Technikum" should have 2 chargers

  Scenario: View chargers of a location
    Given a location with name "FH-Technikum" exists
    And the location "FH-Technikum" has a charger with id 100 of type AC
    When the owner requests all chargers for location "FH-Technikum"
    Then the system should return 1 charger


      # edge case
  Scenario: Adding a charger to a non-existing location should not add any charger
    Given a location with name "FH-Technikum" exists
    When the owner adds a charger with id 999 of type AC to location "Liesing"
    And the owner requests all chargers for location "FH-Technikum"
    Then the system should return 0 charger

