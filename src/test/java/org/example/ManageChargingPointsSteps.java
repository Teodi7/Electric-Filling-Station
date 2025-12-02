package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ManageChargingPointsSteps {

    private LocationManager locationManager = new LocationManager();
    private ChargerManager chargerManager = new ChargerManager();

    private List<Charger> lastResultChargers = new ArrayList<>();

    // -------------------------------------------------------------
    // GIVEN – Location exists
    // -------------------------------------------------------------

    @Given("there is a location with id {int}, name {string}, address {string} and status {string}")
    public void there_is_a_location_with_id_name_address_status(
            Integer id, String name, String address, String status) {

        locationManager.createLocation(id, name, address, status);
    }

    // -------------------------------------------------------------
    // WHEN – Owner creates charger
    // -------------------------------------------------------------

    @When("the owner creates a charger with id {int}, type {string} and status {string} for location with id {int}")
    public void owner_creates_charger_for_location(
            Integer chargerId, String type, String status, Integer locationId) {

        Location loc = locationManager.findLocationById(locationId);
        Assertions.assertNotNull(loc, "Location not found for id " + locationId);

        ChargerType chargerType = ChargerType.valueOf(type);
        ChargerStatus chargerStatus = ChargerStatus.valueOf(status);

        chargerManager.createCharger(chargerId, chargerType, chargerStatus, 22.0, loc);
    }

    // -------------------------------------------------------------
    // THEN – System shows count
    // -------------------------------------------------------------

    @Then("the system should show {int} charger for location with id {int}")
    public void system_should_show_charger_count(Integer expectedCount, Integer locationId) {

        Location loc = locationManager.findLocationById(locationId);
        Assertions.assertNotNull(loc, "Location not found for id " + locationId);

        List<Charger> chargers = chargerManager.getChargersByLocation(loc);
        Assertions.assertEquals(expectedCount.intValue(), chargers.size());
    }

    // -------------------------------------------------------------
    // WHEN – Owner requests list of chargers (do NOT duplicate)
    // -------------------------------------------------------------



}
