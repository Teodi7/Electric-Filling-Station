package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageClientAccountsSteps {

    private ClientAccountManager clientAccountManager = new ClientAccountManager();
    private List<ClientAccount> resultList;

    @Given("there is a client with id {int}, name {string} and email {string} and an active account with balance {double}")
    public void there_is_a_client_with_id_name_and_email_and_an_active_account_with_balance(
            Integer id, String name, String email, Double balance) {

        ClientAccount acc = clientAccountManager.createClientAccount(id, name, email);
        acc.credit(balance);
    }

    @When("the owner requests the list of client accounts")
    public void the_owner_requests_the_list_of_client_accounts() {
        resultList = clientAccountManager.getAllAccounts();
    }

    @Then("the system should return {int} client accounts")
    public void the_system_should_return_client_accounts(Integer expectedCount) {
        assertNotNull(resultList);
        assertEquals(expectedCount.intValue(), resultList.size());
    }
}
