package org.example;

import io.cucumber.java.en.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReadPricesSteps {

    //US 3.1-Read-Prices
    private LocationManager locationManager = new LocationManager();
    private PriceManager priceManager = new PriceManager();
    private List<Price> result;
    private Location currentLocation;
    private Price lastPrice;

    @Given("there is a price location with id {int}, name {string}, address {string} and status {string}")
    public void create_location(int id, String name, String address, String status) {
        currentLocation = new Location(id, name, address, status);
        locationManager.addLocation(currentLocation);
    }


    @Given("a price exists for location with id {int}, charger type {string}, price per kwh {double} and price per minute {double}")
    public void price_exists(Integer locId, String chargerType, Double pricePerKwh, Double pricePerMinute) {
        ChargerType type = ChargerType.valueOf(chargerType);
        priceManager.addPrice(new Price(currentLocation, type, pricePerKwh, pricePerMinute));
    }


    @When("the owner requests the prices for location with id {int}")
    public void owner_requests_prices(Integer id) {
        result = priceManager.getPricesByLocation(currentLocation);
    }

    @Then("the system should return {int} prices for location with id {int}")
    public void verify_price_count(Integer expectedCount, Integer id) {
        assertNotNull(result);
        assertEquals(expectedCount.intValue(), result.size());
    }

    //Update-Prices


    @When("the owner updates the price for location with id {int}, charger type {string} to price per kwh {double} and price per minute {double}")
    public void owner_updates_price(Integer locId, String chargerType,
                                    Double newPricePerKwh, Double newPricePerMinute) {

        ChargerType type = ChargerType.valueOf(chargerType);

        priceManager.updatePrice(currentLocation, type, newPricePerKwh, newPricePerMinute);


        lastPrice = priceManager.getPrice(currentLocation, type);
    }

    @Then("the system should show price per kwh {double} and price per minute {double} for location with id {int} and charger type {string}")
    public void system_should_show_updated_price(Double expectedKwh, Double expectedMinute,
                                                 Integer locId, String chargerType) {

        ChargerType type = ChargerType.valueOf(chargerType);
        Price price = priceManager.getPrice(currentLocation, type);

        assertNotNull(price);
        assertEquals(expectedKwh, price.getPricePerKwh(), 0.0001);
        assertEquals(expectedMinute, price.getPricePerMinute(), 0.0001);
    }
}
