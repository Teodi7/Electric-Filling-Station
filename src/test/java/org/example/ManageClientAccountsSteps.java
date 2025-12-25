package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageClientAccountsSteps {

    private ClientAccountManager clientAccountManager = new ClientAccountManager();
    private List<ClientAccount> resultList;
    private ClientAccount shownAccount;

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

    // Us. 4.2
    @When("the owner updates the client account with id {int} to name {string} and email {string}")
    public void the_owner_updates_the_client_account_with_id_to_name_and_email(Integer accountId,
                                                                               String newName,
                                                                               String newEmail) {

        // Account im Manager suchen
        ClientAccount account = clientAccountManager.getAccountByClientId(accountId);

        // Wenn gefunden, Kontaktdaten ändern
        if (account != null) {
            account.updateContactData(newName, newEmail);
        }

        shownAccount = account;
    }

    @Then("the system should show a client account with id {int}, name {string} and email {string}")
    public void the_system_should_show_a_client_account_with_id_name_and_email(Integer accountId,
                                                                               String expectedName,
                                                                               String expectedEmail) {

        ClientAccount account = clientAccountManager.getAccountByClientId(accountId);

        assertNotNull(account);
        assertEquals(expectedName, account.getName());
        assertEquals(expectedEmail, account.getEmail());
    }
    @When("the owner deletes the client account with id {int}")
    public void the_owner_deletes_the_client_account_with_id(Integer accountId) {
        clientAccountManager.deleteClientAccount(accountId);
    }

    @Then("the system should not contain a client account with id {int}")
    public void the_system_should_not_contain_a_client_account_with_id(Integer accountId) {

        ClientAccount account = clientAccountManager.getAccountByClientId(accountId);

        assertNull(account);
    }
}


