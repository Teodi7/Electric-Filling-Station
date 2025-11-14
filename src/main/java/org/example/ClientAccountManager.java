package org.example;

import java.util.ArrayList;
import java.util.List;

public class ClientAccountManager {

    private List<Client> clients = new ArrayList<>();
    private List<ClientAccount> accounts = new ArrayList<>();

    public ClientAccount createClientAccount(Client client) {
        ClientAccount account = new ClientAccount(client.getClientId(), "ACTIVE", 0.0);
        clients.add(client);
        accounts.add(account);
        return account;
    }

    public void updateClientAccount(ClientAccount account) {

    }

    public void deleteClientAccount(int accountId) {
        accounts.removeIf(account -> account.getAccountId() == accountId);
    }

    public ClientAccount getAccountByClientId(int clientId) {
        for (ClientAccount account : accounts) {
            if (account.getAccountId() == clientId) {
                return account;
            }
        }
        return null;
    }

    public void topUpBalance(int accountId, double amount) {
        ClientAccount account = getAccountByClientId(accountId);
        if (account != null) {
            account.credit(amount);
        }
    }

    public void withdrawBalance(int accountId, double amount) {
        ClientAccount account = getAccountByClientId(accountId);
        if (account != null) {
            account.debit(amount);
        }
    }
}
