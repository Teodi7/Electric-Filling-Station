package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

public class ManageClientAccountsSteps {

    @When("the owner requests all client accounts")
    public void the_owner_requests_all_client_accounts() {
        // checked in assertion
    }

    @Then("the system should return {int} client accounts")
    public void the_system_should_return_client_accounts(Integer expected) {
        Assertions.assertEquals(expected, LocationSteps.getNetwork().getClientAccountManager().getAllClients().size());
    }

    @When("the owner deletes the client account with id {int}")
    public void the_owner_deletes_the_client_account_with_id(Integer id) {
        ClientAccountManager manager = LocationSteps.getNetwork().getClientAccountManager();
        ClientAccount client = manager.getClientById(id);

        // Edge case: client does not exist -> do nothing
        if (client == null) {
            return;
        }

        manager.getAllClients().remove(client);
    }


    @Then("the system should not contain a client account with id {int}")
    public void the_system_should_not_contain_a_client_account_with_id(Integer id) {
        Assertions.assertNull(LocationSteps.getNetwork().getClientAccountManager().getClientById(id));
    }
}
