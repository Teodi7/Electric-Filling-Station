package org.example;

public class Charger {

    private int id;
    private ChargerType type;
    private ChargerStatus status;
    private Location location;

    public Charger(int id, ChargerType type, ChargerStatus status, Location location) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public ChargerType getType() {
        return type;
    }

    public ChargerStatus getStatus() {
        return status;
    }

    public void setStatus(ChargerStatus status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }
}
