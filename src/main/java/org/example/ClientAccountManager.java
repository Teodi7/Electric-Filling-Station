package org.example;

import java.util.ArrayList;
import java.util.List;

public class ClientAccountManager {

    private List<ClientAccount> accounts = new ArrayList<>();

    public ClientAccount createClientAccount(int id, String name, String email) {
        ClientAccount account = new ClientAccount(id, name, email);
        accounts.add(account);
        return account;
    }

    public ClientAccount getAccountByClientId(int id) {
        for (ClientAccount acc : accounts) {
            if (acc.getAccountId() == id) {
                return acc;
            }
        }
        return null;
    }

    public void topUpBalance(int clientId, double amount) {
        ClientAccount acc = getAccountByClientId(clientId);
        if (acc != null) {
            acc.credit(amount);
        }
    }

    public List<ClientAccount> getAllAccounts() {
        return accounts;
    }
}
