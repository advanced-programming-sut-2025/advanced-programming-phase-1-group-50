package com.stardew.models.stores;

import com.stardew.models.tools.PoleType;

public class FishShopPoleItem extends ShopItem{

    private final PoleType type;
    private final int fishingSkillRequired;


    public FishShopPoleItem(String name, PoleType type, int fishingSkillRequired,int price, int dailyLimit) {
        super(name, price, dailyLimit);
        this.type = type;
        this.fishingSkillRequired = fishingSkillRequired;
    }

    public PoleType getType() {
        return type;
    }

    public int getFishingSkillRequired() {
        return fishingSkillRequired;
    }
}
