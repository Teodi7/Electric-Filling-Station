package org.example;


 //US6.1 – View Balance
public class BalanceInquiryManager {

    private final ClientAccountManager accountManager;

    public BalanceInquiryManager(ClientAccountManager accountManager) {
        this.accountManager = accountManager;
    }

    // US6.1 – View Balance
    public double getCurrentBalance(int clientId) {
        ClientAccount account = accountManager.getAccountByClientId(clientId);
        if (account == null) {
            throw new IllegalArgumentException("Client account not found: " + clientId);
        }
        return account.getBalance();
    }
}
