package models.stores;

import models.foraging.ForagingMineral;

public class BlackSmithStocksItem extends StoreItem {

    private final String description;
    private final ForagingMineral type;

    public BlackSmithStocksItem(String name, ForagingMineral type, int price, Integer dailyLimit , String description) {
        super(name, price, dailyLimit);
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public ForagingMineral getType() {
        return type;
    }

}
