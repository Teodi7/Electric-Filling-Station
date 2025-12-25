package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;

/**
 * Steps NUR für ClientInvoice.feature (US9.1)
 */
public class InvoiceSteps {

    private final LocationManager locationManager = new LocationManager();
    private final ChargerManager chargerManager = new ChargerManager();
    private final ClientAccountManager accountManager = new ClientAccountManager();
    private final ChargingSessionManager sessionManager = new ChargingSessionManager();
    private final InvoiceManager invoiceManager = new InvoiceManager();

    private Location location;
    private Charger charger;
    private ClientAccount clientAccount;
    private ChargingSession firstSession;
    private ChargingSession secondSession;
    private List<Invoice> invoiceItems;
    private int nextSessionId = 1;


    @Given("the location with id {int} has an AC charger with id {int}, type {string} and status {string}")
    public void the_location_with_id_has_an_ac_charger_with_id_type_and_status(
            Integer locationId,
            Integer chargerId,
            String type,
            String status
    ) {

        location = locationManager.createLocation(
                locationId,
                "FH-Technikum",
                "Höchstädtplatz 6",
                status
        );

        ChargerType chargerType = ChargerType.valueOf(type);
        ChargerStatus chargerStatus = ChargerStatus.valueOf(status);

        charger = chargerManager.createCharger(
                chargerId,
                chargerType,
                chargerStatus,
                0.0,
                location
        );
    }


    @Given("a client account with id {int} and an initial balance of {double}")
    public void a_client_account_with_id_and_an_initial_balance_of(Integer id, Double initialBalance) {
        clientAccount = new ClientAccount(id, "Client" + id, "client" + id + "@example.com");
        clientAccount.setBalance(initialBalance);
        accountManager.addAccount(clientAccount);
    }

    @Given("the client completes a charging session of {double} kWh and {int} minutes on charger with id {int} at location {int}")
    public void the_client_completes_a_charging_session_of_k_wh_and_minutes_on_charger_with_id_at_location(
            Double kwh,
            Integer minutes,
            Integer chargerId,
            Integer locationId
    ) {
        int sessionId = nextSessionId++;

        firstSession = sessionManager.startSession(clientAccount, charger, sessionId);
        if (firstSession != null) {
            firstSession.setEnergyKWh(kwh);
            sessionManager.stopSession(firstSession.getSessionId());
            invoiceManager.createInvoice(clientAccount);
        }
    }


    @Given("the client tops up their balance by {double}")
    public void the_client_tops_up_their_balance_by(Double amount) {
        if (clientAccount != null) {
            accountManager.topUpBalance(clientAccount.getAccountId(), amount);
            invoiceManager.createInvoice(clientAccount);
        }
    }


    @Given("the client completes another charging session of {double} kWh and {int} minutes on charger with id {int} at location {int}")
    public void the_client_completes_another_charging_session_of_k_wh_and_minutes_on_charger_with_id_at_location(
            Double kwh,
            Integer minutes,
            Integer chargerId,
            Integer locationId
    ) {
        int sessionId = nextSessionId++;

        secondSession = sessionManager.startSession(clientAccount, charger, sessionId);
        if (secondSession != null) {
            secondSession.setEnergyKWh(kwh);
            sessionManager.stopSession(secondSession.getSessionId());
            invoiceManager.createInvoice(clientAccount);
        }
    }


    @When("the client requests their detailed invoice items")
    public void the_client_requests_their_detailed_invoice_items() {
        if (clientAccount != null) {
            invoiceItems = invoiceManager.getInvoicesByAccount(clientAccount);
        }
    }


    @Then("the system should return an invoice item for each charging session including start time, duration, energy consumed and applied rates")
    public void the_system_should_return_an_invoice_item_for_each_charging_session_including_start_time_duration_energy_consumed_and_applied_rates() {

    }


    @Then("the system should return an invoice item for the top-up with the credited amount")
    public void the_system_should_return_an_invoice_item_for_the_top_up_with_the_credited_amount() {

    }


    @Then("each invoice item should include the remaining balance after the operation")
    public void each_invoice_item_should_include_the_remaining_balance_after_the_operation() {

    }
}
