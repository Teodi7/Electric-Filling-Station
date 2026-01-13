package org.example;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private int id;
    private String name;
    private String address;
    private List<Charger> chargers = new ArrayList<>();

    public Location(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Charger getChargerById(int id) {
        return chargers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removeChargerById(int id) {
        chargers.removeIf(c -> c.getId() == id);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Charger> getChargers() {
        return chargers;
    }

    public void addCharger(Charger charger) {
        chargers.add(charger);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
