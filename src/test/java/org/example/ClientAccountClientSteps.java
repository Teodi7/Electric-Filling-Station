package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class ClientAccountClientSteps {

    private ClientAccountManager clientAccountManager = new ClientAccountManager();
    private ClientAccount account;        // <-- EINZIGE Variable
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
        assertEquals(expectedBalance, shownAccount.getBalance());
    }

    @When("the client updates his account data to name {string} and email {string}")
    public void the_client_updates_his_account_data_to_name_and_email(String newName, String newEmail) {


        account.updateContactData(newName, newEmail);
    }

    @Then("the system should show client account with id {int} having name {string} and email {string}")
    public void the_system_should_show_client_account_with_id_having_name_and_email(Integer accountId,
                                                                                    String expectedName,
                                                                                    String expectedEmail) {

        ClientAccount acc = clientAccountManager.getAccountByClientId(accountId);

        assertNotNull(acc);
        assertEquals(expectedName, acc.getName());
        assertEquals(expectedEmail, acc.getEmail());
    }

    @When("the client deletes his account with id {int}")
    public void the_client_deletes_his_account_with_id(Integer accountId) {
        clientAccountManager.deleteClientAccount(accountId);
    }

    @Then("the system should not show any account with id {int}")
    public void the_system_should_not_show_any_account_with_id(Integer accountId) {
        ClientAccount acc = clientAccountManager.getAccountByClientId(accountId);
        assertNull(acc);
    }
}
