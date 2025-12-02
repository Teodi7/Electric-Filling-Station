package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTopUpSteps {

    private ClientAccountManager clientAccountManager = new ClientAccountManager();
    private ClientAccount account;

    @Given("a client with id {int}, name {string} and email {string} has an active account with balance {double}")
    public void a_client_with_id_name_and_email_has_an_active_account_with_balance(
            Integer id, String name, String email, Double balance) {

        account = clientAccountManager.createClientAccount(id, name, email);
        account.credit(balance);
    }

    @When("the client tops up his account by {double}")
    public void the_client_tops_up_his_account_by(Double amount) {
        clientAccountManager.topUpBalance(account.getAccountId(), amount);
    }

    @Then("the account balance should be {double}")
    public void the_account_balance_should_be(Double expectedBalance) {
        ClientAccount refreshed =
                clientAccountManager.getAccountByClientId(account.getAccountId());

        assertEquals(expectedBalance, refreshed.getBalance());
    }
}
