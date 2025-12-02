package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class ClientAccountClientSteps {

    private ClientAccountManager clientAccountManager = new ClientAccountManager();
    private ClientAccount account;
    private ClientAccount shownAccount;

    @Given("a client with id {int}, name {string} and email {string} registers in the system")
    public void a_client_with_id_name_and_email_registers_in_the_system(
            Integer id, String name, String email) {

        account = clientAccountManager.createClientAccount(id, name, email);
    }

    @When("the client requests his account data")
    public void the_client_requests_his_account_data() {
        shownAccount = clientAccountManager.getAccountByClientId(account.getAccountId());
    }

    @Then("the system should show an active account with balance {double}")
    public void the_system_should_show_an_active_account_with_balance(Double expectedBalance) {
        assertNotNull(shownAccount);
        assertEquals("ACTIVE", shownAccount.getStatus());
        assertEquals(expectedBalance, shownAccount.getBalance());
    }
}
