package org.example;

import java.util.ArrayList;
import java.util.List;


 // Verwaltet alle ClientAccounts.

public class ClientAccountManager {

    private List<ClientAccount> accounts = new ArrayList<>();

    // US5.1 – Create Client Account (Client)
    public ClientAccount createClientAccount(int id, String name, String email) {
        ClientAccount account = new ClientAccount(id, name, email);
        accounts.add(account);
        return account;
    }

    // US4.1 / US5.4 – Read Client Account
    public ClientAccount getAccountByClientId(int id) {
        for (ClientAccount acc : accounts) {
            if (acc.getAccountId() == id) {
                return acc;
            }
        }
        return null;
    }

    // US4.3 / US5.3 – Delete Client Account
    public void deleteClientAccount(int accountId) {
        accounts.removeIf(account -> account.getAccountId() == accountId);
    }

    // US6.2 – Top-Up Balance
    public void topUpBalance(int clientId, double amount) {
        ClientAccount acc = getAccountByClientId(clientId);
        if (acc != null) {
            acc.credit(amount);
        }
    }

    // US6.3 – Withdraw Balance
    public double withdrawAllBalance(int clientId) {
        ClientAccount acc = getAccountByClientId(clientId);
        if (acc == null) {
            return 0.0;
        }
        double withdrawn = acc.getBalance();
        acc.setBalance(0.0);
        return withdrawn;
    }

    // US4.1 – Read Client Account (Liste)
    public List<ClientAccount> getAllAccounts() {
        return accounts;
    }

    public void addAccount(ClientAccount account) {
        if (account != null) {
            accounts.add(account);
        }
    }
}
