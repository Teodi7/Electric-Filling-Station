Feature: Manage locations
  In order to operate multiple charging stations
  As the owner
  I want to create and view locations

  Scenario: Owner creates a new location
    Given there is no location with id 1
    When the owner creates a new location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    Then the system should contain a location with id 1
    And the location name should be "FH-Technikum"
    And the location address should be "Höchstädtplatz 6"
    And the location status should be "AVAILABLE"

  Scenario: Owner views all locations
    Given there is an existing location with id 1, name "FH-Technikum", address "Höchstädtplatz 6" and status "AVAILABLE"
    And there is an existing location with id 2, name "City Center", address "1010 Vienna" and status "AVAILABLE"
    When the owner requests the list of locations
    Then the system should return 2 locations
