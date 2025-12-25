package org.example;

import java.util.ArrayList;
import java.util.List;

// fürLade-Standort.

public class Location {

    private int locationId;
    private String name;
    private String address;
    private String status;
    private List<Charger> chargers = new ArrayList<>();

    public Location(int locationId, String name, String address, String status) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
        this.status = status;
    }


    public Location() {
    }

    // Charger-Verwaltung  E2 / E7
    public void addCharger(Charger charger) {
        if (charger != null && !chargers.contains(charger)) {
            chargers.add(charger);
        }
    }

    public void removeCharger(Charger charger) {
        chargers.remove(charger);
    }

    public List<Charger> getAvailableChargers() {
        List<Charger> available = new ArrayList<>();
        for (Charger charger : chargers) {
            if (charger.isAvailable()) {
                available.add(charger);
            }
        }
        return available;
    }

    // Getter/Setter
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Charger> getChargers() {
        return chargers;
    }

    public void setChargers(List<Charger> chargers) {
        this.chargers = chargers;
    }
}
