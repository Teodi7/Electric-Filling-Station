package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class ViewBalanceSteps {

    private ClientAccountManager accountManager;
    private BalanceInquiryManager balanceInquiryManager;

    private int currentClientId;
    private double displayedBalance;

    @Given("an existing client account with id {int} and balance {double}")
    public void an_existing_client_account_with_id_and_balance(Integer id, Double balance) {

        accountManager = new ClientAccountManager();
        balanceInquiryManager = new BalanceInquiryManager(accountManager);

        currentClientId = id;

        ClientAccount account = new ClientAccount(id, "Client" + id, "client" + id + "@example.com");
        account.setBalance(balance);
        accountManager.addAccount(account);

        ClientAccount stored = accountManager.getAccountByClientId(id);
        assertNotNull(stored);
        assertEquals(balance, stored.getBalance());
    }

    @When("the client checks their current balance")
    public void the_client_checks_their_current_balance() {
        displayedBalance = balanceInquiryManager.getCurrentBalance(currentClientId);
    }

    @Then("the system shows a balance of {double}")
    public void the_system_shows_a_balance_of(Double expectedBalance) {
        assertEquals(expectedBalance, displayedBalance);
    }
}
