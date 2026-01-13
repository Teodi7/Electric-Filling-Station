package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceManager {

    private List<Price> prices = new ArrayList<>();

    // Preis hinzufügen (wird z.B. beim ersten Setzen verwendet)
    public void addPrice(Price price) {
        prices.add(price);
    }

    // Wird in deinen Steps verwendet
    public void setPrice(Location location, ChargerType chargerType, double pricePerKwh) {
        prices.add(new Price(location, chargerType, pricePerKwh));
    }

    // Update = neuer Preiseintrag mit neuer Uhrzeit
    public void updatePrice(Location location, ChargerType chargerType, double newPricePerKwh) {
        prices.add(new Price(location, chargerType, newPricePerKwh));
    }

    // Alle Preise für eine Location (egal ob AC/DC)
    public List<Price> getPricesForLocation(Location location) {
        return prices.stream()
                .filter(p -> p.getLocation().equals(location))
                .collect(Collectors.toList());
    }

    //  WICHTIG: wirklich neuester Preis nach Uhrzeit
    public Price getLatestPrice(Location location, ChargerType chargerType) {
        return prices.stream()
                .filter(p -> p.getLocation().equals(location))
                .filter(p -> p.getChargerType() == chargerType)
                .max(Comparator.comparing(Price::getTimestamp))
                .orElse(null);
    }
}
