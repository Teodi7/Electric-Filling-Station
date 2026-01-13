package org.example;

import java.util.ArrayList;
import java.util.List;

public class ClientAccountManager {

    private List<ClientAccount> accounts = new ArrayList<>();

    public void addClient(ClientAccount account) {
        accounts.add(account);
    }

    public ClientAccount getClientById(int id) {
        for (ClientAccount a : accounts) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public List<ClientAccount> getAllClients() {
        return accounts;
    }

    public void topUpBalance(int clientId, double amount) {

        // Edge case: negativer oder 0 Betrag
        if (amount <= 0) {
            return;
        }

        ClientAccount client = getClientById(clientId);
        if (client == null) {
            return;
        }

        client.topUp(amount);
    }

}
