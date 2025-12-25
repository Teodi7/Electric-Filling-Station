package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ChargeEVSteps {

    private LocationManager locationManager = new LocationManager();
    private ChargerManager chargerManager = new ChargerManager();
    private PriceManager priceManager = new PriceManager();
    private ClientAccountManager accountManager = new ClientAccountManager();
    private ChargingSessionManager sessionManager = new ChargingSessionManager();

    private Location currentLocation;
    private Charger currentCharger;
    private ClientAccount currentAccount;
    private Price currentPrice;
    private ChargingSession currentSession;

    private double calculatedCost;



    @Given("a client account with id {int}, name {string} and email {string} and balance {double}")
    public void a_client_account_with_id_name_and_email_and_balance(Integer id,
                                                                    String name,
                                                                    String email,
                                                                    Double balance) {

        currentAccount = accountManager.createClientAccount(id, name, email);
        currentAccount.credit(balance);
    }

    @Given("a location with id {int}, name {string}, address {string} and status {string}")
    public void a_location_with_id_name_address_and_status(Integer id,
                                                           String name,
                                                           String address,
                                                           String status) {

        currentLocation = new Location(id, name, address, status);
        locationManager.addLocation(currentLocation);
    }

    @Given("a charger with id {int}, type {string} and status {string} at that location")
    public void a_charger_with_id_type_and_status_at_that_location(Integer chargerId,
                                                                   String type,
                                                                   String status) {

        ChargerType chargerType = ChargerType.valueOf(type);
        ChargerStatus chargerStatus = ChargerStatus.valueOf(status);

        currentCharger = chargerManager.createCharger(
                chargerId,
                chargerType,
                chargerStatus,
                22.0,
                currentLocation
        );
    }

    @Given("a price for this location and charger type {string} with price per kwh {double} and price per minute {double}")
    public void a_price_for_this_location_and_charger_type_with_price_per_kwh_and_price_per_minute(String type,
                                                                                                   Double pricePerKwh,
                                                                                                   Double pricePerMinute) {

        ChargerType chargerType = ChargerType.valueOf(type);
        currentPrice = new Price(currentLocation, chargerType, pricePerKwh, pricePerMinute);
        priceManager.addPrice(currentPrice);
    }



    @When("the client starts a charging session on charger with id {int}")
    public void the_client_starts_a_charging_session_on_charger_with_id(Integer sessionId) {

        // sessionId aus Scenario kommt (hier 100)
        currentSession = sessionManager.startSession(currentAccount, currentCharger, sessionId);
        assertNotNull(currentSession);
    }

    @When("the client charges {double} kwh for {int} minutes")
    public void the_client_charges_kwh_for_minutes(Double energyKwh, Integer minutes) {


        currentSession.setEnergyKWh(energyKwh);

        calculatedCost = currentPrice.calculateCost(energyKwh, minutes);

        // Zahlung vom Konto
        if (currentAccount.canPay(calculatedCost)) {
            currentAccount.debit(calculatedCost);
        } else {
            fail("Account has not enough balance for the charging cost.");
        }
    }



    @Then("the total charging cost should be {double}")
    public void the_total_charging_cost_should_be(Double expectedCost) {
        assertEquals(expectedCost, calculatedCost, 0.0001);
    }

    @Then("the client account balance should be {double}")
    public void the_client_account_balance_should_be(Double expectedBalance) {
        assertNotNull(currentAccount);
        assertEquals(expectedBalance, currentAccount.getBalance(), 0.0001);
    }
}
