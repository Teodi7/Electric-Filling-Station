package org.example;

import java.util.ArrayList;
import java.util.List;

public class PriceManager {

    private List<Price> prices = new ArrayList<>();

    public Price getPrice(Location location, ChargerType chargerType) {
        for (Price price : prices) {
            if (price.getChargerType() == chargerType) {
                return price;
            }
        }
        return null;
    }

    public void updatePrice(Price price) {

    }

    public List<Price> getAllPrices() {
        return prices;
    }

    public void addPrice(Price price) {
        prices.add(price);
    }
}
