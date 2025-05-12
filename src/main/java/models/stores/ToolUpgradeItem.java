package models.stores;

import models.manuFactor.artisanGoods.ArtisanGoodType;

public class ToolUpgradeItem extends StoreItem{

    private final int requiredAmountForUpgrade;
    private final ArtisanGoodType goodTypeForUpgrade;

    public ToolUpgradeItem(String name, ArtisanGoodType goodTypeForUpgrade, int requiredAmountForUpgrade ,int price, Integer dailyLimit) {
        super(name, price, dailyLimit);
        this.goodTypeForUpgrade = goodTypeForUpgrade;
        this.requiredAmountForUpgrade = requiredAmountForUpgrade;
    }

    public int getRequiredAmountForUpgrade() {
        return requiredAmountForUpgrade;
    }

    public ArtisanGoodType getGoodTypeForUpgrade() {
        return goodTypeForUpgrade;
    }

}
