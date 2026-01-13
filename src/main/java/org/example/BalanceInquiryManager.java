package org.example;

public class BalanceInquiryManager {

    public double getCurrentBalance(ClientAccount clientAccount) {
        if (clientAccount == null) {
            return 0.0;
        }
        return clientAccount.getBalance();
    }
}
