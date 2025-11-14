package org.example;

public class Client {

    private int clientId;
    private String name;
    private String email;

    public Client(int clientId, String name, String email) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
    }

    public Client() {
    }


    public void updateContactData(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
