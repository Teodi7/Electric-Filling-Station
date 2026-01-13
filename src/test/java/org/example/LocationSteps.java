package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class LocationSteps {

    //  resetet bevor jedem scenario
    private static ElectricChargingStationNetwork network;

    public static ElectricChargingStationNetwork getNetwork() {
        return network;
    }

    @Before
    public void resetSystem() {
        network = new ElectricChargingStationNetwork();
        ChargeEVSteps.resetLastInvoice();
    }

    private Location lastCreatedLocation;

    @Given("there is no location with id {int}")
    public void there_is_no_location_with_id(Integer id) {
        Assertions.assertNull(network.getLocationManager().getLocationById(id));
    }

    @When("the owner creates a location with id {int}, name {string}, address {string}")
    public void the_owner_creates_a_location_with_id_name_address(Integer id, String name, String address) {
        lastCreatedLocation = new Location(id, name, address);
        network.getLocationManager().addLocation(lastCreatedLocation);
    }

    @Then("the system should contain a location with id {int}")
    public void the_system_should_contain_a_location_with_id(Integer id) {
        Assertions.assertNotNull(network.getLocationManager().getLocationById(id));
    }

    @Then("the location name should be {string}")
    public void the_location_name_should_be(String expected) {
        Assertions.assertNotNull(lastCreatedLocation);
        Assertions.assertEquals(expected, lastCreatedLocation.getName());
    }

    @Then("the location address should be {string}")
    public void the_location_address_should_be(String expected) {
        Assertions.assertNotNull(lastCreatedLocation);
        Assertions.assertEquals(expected, lastCreatedLocation.getAddress());
    }

    @Given("the following locations exist:")
    public void the_following_locations_exist(DataTable table) {
        table.asMaps(String.class, String.class).forEach(row -> {
            int id = Integer.parseInt(row.get("id"));
            String name = row.get("name");
            String address = row.get("address");
            network.getLocationManager().addLocation(new Location(id, name, address));
        });
    }

    @When("the owner requests all locations")
    public void the_owner_requests_all_locations() {
        // result is checked in assertion
    }

    @Then("the system should return {int} locations")
    public void the_system_should_return_locations(Integer expected) {
        Assertions.assertEquals(expected, network.getLocationManager().getAllLocations().size());
    }

    @Given("a location with id {int}, name {string}, address {string} exists")
    public void a_location_with_id_name_address_exists(Integer id, String name, String address) {
        lastCreatedLocation = new Location(id, name, address);
        network.getLocationManager().addLocation(lastCreatedLocation);
    }

    @When("the owner updates the location with id {int} to name {string}, address {string}")
    public void the_owner_updates_the_location_with_id_to_name_address(Integer id, String name, String address) {
        Location loc = network.getLocationManager().getLocationById(id);
        Assertions.assertNotNull(loc);
        loc.setName(name);
        loc.setAddress(address);
    }

    @Then("the location with id {int} should have name {string}")
    public void the_location_with_id_should_have_name(Integer id, String expectedName) {
        Location loc = network.getLocationManager().getLocationById(id);
        Assertions.assertNotNull(loc);
        Assertions.assertEquals(expectedName, loc.getName());
    }

    //mit edge case
    @When("the owner deletes the location with id {int}")
    public void the_owner_deletes_the_location_with_id(Integer id) {
        Location loc = LocationSteps.getNetwork().getLocationManager().getLocationById(id);

        // fürEdge case
        if (loc != null) {
            LocationSteps.getNetwork().getLocationManager().getAllLocations().remove(loc);
        }
    }

    @Then("the system should not contain a location with id {int}")
    public void the_system_should_not_contain_a_location_with_id(Integer id) {
        Assertions.assertNull(network.getLocationManager().getLocationById(id));
    }

    // verwende ich in andere features
    @Given("a location with name {string} exists")
    public void a_location_with_name_exists(String name) {
        lastCreatedLocation = new Location(1, name, "Höchstädtplatz 6");
        network.getLocationManager().addLocation(lastCreatedLocation);
    }
}
