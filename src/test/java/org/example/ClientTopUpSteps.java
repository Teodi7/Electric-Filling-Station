package org.example;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;

public class ClientTopUpSteps {

    private Integer lastClientId;

    @Given("a client with id {int}, name {string}, email {string} exists")
    public void a_client_with_id_name_email_exists(Integer id, String name, String email) {
        ClientAccount client = new ClientAccount(id, name, email);
        LocationSteps.getNetwork().getClientAccountManager().addClient(client);
        lastClientId = id;
    }

    @Given("the following clients exist:")
    public void the_following_clients_exist(DataTable table) {
        table.asMaps(String.class, String.class).forEach(row -> {
            int id = Integer.parseInt(row.get("id"));
            String name = row.get("name");
            String email = row.get("email");
            LocationSteps.getNetwork().getClientAccountManager().addClient(new ClientAccount(id, name, email));
        });
    }

    @When("the client with id {int} tops up the balance by {double} EUR")
    public void the_client_with_id_tops_up_the_balance_by_eur(Integer id, Double amount) {
        LocationSteps.getNetwork().getClientAccountManager().topUpBalance(id, amount);
        lastClientId = id;
    }

    @Then("the client balance should be {double} EUR")
    public void the_client_balance_should_be_eur(Double expected) {
        Assertions.assertNotNull(lastClientId, "No client was used in this scenario");
        ClientAccount client = LocationSteps.getNetwork().getClientAccountManager().getClientById(lastClientId);
        Assertions.assertNotNull(client);
        Assertions.assertEquals(expected, client.getBalance(), 0.01);
    }
}
