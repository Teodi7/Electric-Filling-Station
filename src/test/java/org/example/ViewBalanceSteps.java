package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class ViewBalanceSteps {

    private ClientAccount requestedClient;
    private final BalanceInquiryManager balanceInquiryManager = new BalanceInquiryManager();

    @When("the client requests the current balance")
    public void the_client_requests_the_current_balance() {
        ClientAccountManager manager = LocationSteps.getNetwork().getClientAccountManager();
        requestedClient = manager.getClientById(1);
        if (requestedClient == null && !manager.getAllClients().isEmpty()) {
            requestedClient = manager.getAllClients().get(0);
        }
        Assertions.assertNotNull(requestedClient, "No client exists for balance inquiry");
    }

    @Then("the balance should be {double} EUR")
    public void the_balance_should_be_eur(Double expected) {
        Assertions.assertNotNull(requestedClient);
        Assertions.assertEquals(expected, balanceInquiryManager.getCurrentBalance(requestedClient), 0.01);
    }
}
