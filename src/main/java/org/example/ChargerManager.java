package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChargerManager {

    private List<Charger> chargers = new ArrayList<>();

    public Charger createCharger(int chargerId,
                                 ChargerType type,
                                 ChargerStatus status,
                                 double v, Location location) {

        Charger charger = new Charger(chargerId, type, status);
        chargers.add(charger);
        location.addCharger(charger);
        return charger;
    }

    public void updateCharger(Charger charger) {

    }

    public void deleteCharger(int chargerId) {
        chargers.removeIf(charger -> charger.getChargerId() == chargerId);
    }

    public List<Charger> getChargersByLocation(Location location) {
        return location.getChargers();
    }
}
