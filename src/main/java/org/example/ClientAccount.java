package org.example;


public class ClientAccount {

    // clientId ist auch die Account-ID
    private int clientId;
    private String name;
    private String email;
    private double balance = 0.0;

    // US5.1 – Create Client Account
    public ClientAccount(int clientId, String name, String email) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
    }


    public int getClientId() {
        return clientId;
    }

    public int getAccountId() {
        return clientId;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // US4.2 / US5.2 – Update Client Account
    public void updateContactData(String newName, String newEmail) {
        this.name = newName;
        this.email = newEmail;
    }

    // Ganze Guthaben

    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    // US6.2 – Top-Up Balance
    public void credit(double amount) {
        balance += amount;
    }

    // US6.1 / US6.3 / US8.1 – Prüfung,
    public boolean canPay(double amount) {
        return balance >= amount;
    }


    public void debit(double amount) {
        balance -= amount;
    }
}
