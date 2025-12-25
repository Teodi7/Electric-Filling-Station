package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class LocationSteps {

    private LocationManager locationManager = new LocationManager();
    private Location createdLocation;
    private List<Location> returnedLocations;


    private Location findLocation(int id) {
        return locationManager.getAllLocations()
                .stream()
                .filter(l -> l.getLocationId() == id)
                .findFirst()
                .orElse(null);
    }

    // Scenario 1 – Create a new location

    @Given("there is no location with id {int}")
    public void there_is_no_location_with_id(Integer id) {
        Assertions.assertNull(findLocation(id),
                "Expected no location with id " + id + ". But one exists.");
    }

    @When("the owner creates a new location with id {int}, name {string}, address {string} and status {string}")
    public void owner_creates_new_location(
            Integer id, String name, String address, String status) {

        locationManager.createLocation(id, name, address, status);
        createdLocation = findLocation(id);
    }

    @Then("the system should contain a location with id {int}")
    public void system_should_contain_location(Integer id) {
        createdLocation = findLocation(id);
        Assertions.assertNotNull(createdLocation,
                "Location with id " + id + " not found.");
    }

    @Then("the location name should be {string}")
    public void location_name_should_be(String expectedName) {
        Assertions.assertEquals(expectedName, createdLocation.getName());
    }

    @Then("the location address should be {string}")
    public void location_address_should_be(String expectedAddress) {
        Assertions.assertEquals(expectedAddress, createdLocation.getAddress());
    }

    @Then("the location status should be {string}")
    public void location_status_should_be(String expectedStatus) {
        Assertions.assertEquals(expectedStatus, createdLocation.getStatus());
    }


    // Scenario 2 – View locations

    @Given("there is an existing location with id {int}, name {string}, address {string} and status {string}")
    public void given_existing_location(Integer id, String name, String address, String status) {
        locationManager.createLocation(id, name, address, status);
    }

    @When("the owner requests the list of locations")
    public void owner_requests_list_of_locations() {
        returnedLocations = locationManager.getAllLocations();
    }

    @Then("the system should return {int} locations")
    public void system_should_return_locations(Integer expectedCount) {
        Assertions.assertEquals(expectedCount.intValue(), returnedLocations.size());
    }

    // Scenario 3
    @When("the owner updates the location with id {int} to name {string}, address {string} and status {string}")
    public void the_owner_updates_the_location_with_id(Integer id, String newName, String newAddress, String newStatus) {
        locationManager.updateLocation(id, newName, newAddress, newStatus);
        createdLocation = locationManager.findLocationById(id);
    }

    //Scenario 4
    @When("the owner deletes the location with id {int}")
    public void the_owner_deletes_the_location_with_id(Integer id) {
        locationManager.deleteLocation(id);
    }

    @Then("the system should not contain a location with id {int}")
    public void the_system_should_not_contain_a_location_with_id(Integer id) {
        Location found = locationManager.findLocationById(id);
        Assertions.assertNull(found, "Expected no location with id " + id + " but one was found.");
    }
}
