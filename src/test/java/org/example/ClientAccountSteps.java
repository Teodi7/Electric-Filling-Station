package org.example;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientAccountSteps {

    private ClientAccountManager manager = new ClientAccountManager();
    private ClientAccount createdAccount;
    private ClientAccount foundAccount;

    @Given("a client account with id {int}, name {string} and email {string} is created")
    public void aClientAccountIsCreated(int id, String name, String email) {
        createdAccount = manager.createClientAccount(id, name, email);
    }

    @When("the system searches for the client account with id {int}")
    public void theSystemSearchesForClientAccount(int id) {
        foundAccount = manager.getAccountByClientId(id);
    }

    @Then("the returned client account should have the name {string} and email {string}")
    public void theReturnedClientAccountMatches(String name, String email) {
        assertNotNull(foundAccount);
        assertEquals(name, foundAccount.getName());
        assertEquals(email, foundAccount.getEmail());
    }
}