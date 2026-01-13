package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PriceManager {

    private List<Price> prices = new ArrayList<>();

    // SET price (neu)
    public void setPrice(Location location, ChargerType type, double pricePerKwh) {
        prices.add(new Price(location, type, pricePerKwh, LocalDate.now()));
    }

    // Update = neuer Preis mit neuem Datum
    public void updatePrice(Location location, ChargerType type, double pricePerKwh) {
        setPrice(location, type, pricePerKwh);
    }

    public Price getLatestPrice(Location location, ChargerType type) {
        Price latest = null;
        for (Price p : prices) {
            if (p.getLocation().equals(location)
                    && p.getChargerType() == type) {
                latest = p;
            }
        }
        return latest;
    }

    public List<Price> getPricesForLocation(Location location) {
        List<Price> result = new ArrayList<>();
        for (Price p : prices) {
            if (p.getLocation().equals(location)) {
                result.add(p);
            }
        }
        return result;
    }
}
