package org.example;

public class ClientAccount {

    private int accountId;
    private String status; // "ACTIVE", "BLOCKED"
    private double balance;

    public ClientAccount(int accountId, String status, double balance) {
        this.accountId = accountId;
        this.status = status;
        this.balance = balance;
    }

    public ClientAccount() {
    }


    public void credit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void debit(double amount) {
        if (amount > 0 && canPay(amount)) {
            balance -= amount;
        }
    }

    public boolean canPay(double amount) {
        return amount >= 0 && balance >= amount && "ACTIVE".equalsIgnoreCase(status);
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
