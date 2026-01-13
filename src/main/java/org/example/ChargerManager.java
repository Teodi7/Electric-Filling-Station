package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChargerManager {

    private List<Charger> chargers = new ArrayList<>();

    public void addCharger(Charger charger) {
        chargers.add(charger);
        charger.getLocation().addCharger(charger);
    }

    public Charger getChargerById(int id) {
        for (Charger c : chargers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Charger> getChargersByLocation(Location location) {
        List<Charger> result = new ArrayList<>();
        for (Charger c : chargers) {
            if (c.getLocation().equals(location)) {
                result.add(c);
            }
        }
        return result;
    }
}
