package org.example;

import java.util.ArrayList;
import java.util.List;


 // Verwalter für alle Preise.
 // E3 – Manage Prices (Owner Perspective)

public class PriceManager {

    private final List<Price> prices = new ArrayList<>();

    // US3.1 – Read Prices

    public Price getPrice(Location location, ChargerType chargerType) {
        if (location == null || chargerType == null) {
            return null;
        }

        for (Price price : prices) {
            if (price.getLocation().getLocationId() == location.getLocationId()
                    && price.getChargerType() == chargerType) {
                return price;
            }
        }
        return null;
    }

    // US3.1 – Read Prices
    public List<Price> getAllPrices() {
        return new ArrayList<>(prices);
    }


    public void addPrice(Price price) {
        if (price != null) {
            prices.add(price);
        }
    }

    // US3.2 – Update Prices
    public void updatePrice(Location location, ChargerType chargerType,
                            double newPricePerKwh, double newPricePerMinute) {

        Price price = getPrice(location, chargerType);
        if (price != null) {
            price.setPricePerKwh(newPricePerKwh);
            price.setPricePerMinute(newPricePerMinute);
        }
    }

    // US3.1 – Read Prices
    public List<Price> getPricesByLocation(Location location) {
        List<Price> result = new ArrayList<>();
        if (location == null) {
            return result;
        }

        for (Price price : prices) {
            if (price.getLocation().getLocationId() == location.getLocationId()) {
                result.add(price);
            }
        }
        return result;
    }
}
