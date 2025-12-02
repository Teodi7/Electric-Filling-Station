package org.example;

public class ClientAccount {

    private int accountId;     // ersetzt clientId
    private String name;
    private String email;
    private String status = "ACTIVE";
    private double balance = 0.0;

    public ClientAccount(int accountId, String name, String email) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
    }

    public ClientAccount() {}

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance -= amount;
    }

    public void updateContactData(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
