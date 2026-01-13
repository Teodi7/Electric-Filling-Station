package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class ReadPricesSteps {

    private Location lastLocation;

    @Given("a price for AC chargers at {string} is set to {double} EUR per kWh")
    public void a_price_for_ac_chargers_at_is_set_to_eur_per_k_wh(String locationName, Double value) {
        lastLocation = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(lastLocation);

        LocationSteps.getNetwork().getPriceManager().setPrice(lastLocation, ChargerType.AC, value);
    }

    @Given("a price for DC chargers at {string} is set to {double} EUR per kWh")
    public void a_price_for_dc_chargers_at_is_set_to_eur_per_k_wh(String locationName, Double value) {
        lastLocation = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(lastLocation);

        LocationSteps.getNetwork().getPriceManager().setPrice(lastLocation, ChargerType.DC, value);
    }

    @When("the owner requests prices for location {string}")
    public void the_owner_requests_prices_for_location(String locationName) {
        lastLocation = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(lastLocation);
    }


    @Then("the system should return {int} prices")
    public void the_system_should_return_prices(Integer expected) {
        Assertions.assertNotNull(lastLocation);
        Assertions.assertEquals(expected, LocationSteps.getNetwork().getPriceManager().getPricesForLocation(lastLocation).size());
    }

    @When("the owner updates the price for AC chargers at {string} to {double} EUR per kWh")
    public void the_owner_updates_the_price_for_ac_chargers_at_to_eur_per_k_wh(String locationName, Double value) {
        lastLocation = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(lastLocation);

        LocationSteps.getNetwork().getPriceManager().updatePrice(lastLocation, ChargerType.AC, value);
    }

    @Then("the latest price for AC chargers at {string} should be {double} EUR per kWh")
    public void the_latest_price_for_ac_chargers_at_should_be_eur_per_k_wh(String locationName, Double expected) {
        Location loc = LocationSteps.getNetwork().getLocationManager().getLocationByName(locationName);
        Assertions.assertNotNull(loc);

        Price latest = LocationSteps.getNetwork().getPriceManager().getLatestPrice(loc, ChargerType.AC);
        Assertions.assertNotNull(latest);
        Assertions.assertEquals(expected, latest.getPricePerKwh(), 0.01);
    }


}
