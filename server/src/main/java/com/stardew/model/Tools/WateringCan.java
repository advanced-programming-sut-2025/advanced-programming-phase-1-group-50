package com.stardew.model.Tools;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Ability;

public class WateringCan extends Tool {
    private ToolType type = ToolType.Primary;
    private int capacity = 40;
    private int waterCapacity = capacity;
    private TextureID textureRegion = TextureID.wateringCanTexture;

    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            this.capacity = 55;
            this.textureRegion = TextureID.copperWateringCanTexture;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            this.capacity = 70;
            this.textureRegion = TextureID.steelWateringCanTexture;
        } else if (this.type == ToolType.Metal) {
            this.type = ToolType.Golden;
            this.capacity = 85;
            this.textureRegion = TextureID.goldWateringCanTexture;
        } else if (this.type == ToolType.Golden) {
            this.type = ToolType.Iridium;
            this.capacity = 100;
            this.textureRegion = TextureID.iridiumWateringCanTexture;
        }
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
        Weather weather = App.getGame().getTime().getWeather();
        int multiple = switch (weather) {
            case Rainy -> 2;
            case Snowy -> 3;
            default -> 1;
        };
        int consumedEnergy;
        if (App.getGame().getCurrentPlayingPlayer().getAbility().getFarmingLevel() == Ability.getMaxLevel()) {
            consumedEnergy = switch (type) {
                case Primary -> 4 * multiple;
                case Coppery -> 3 * multiple;
                case Metal -> 2 * multiple;
                case Golden -> 1;
                default -> 0;
            };
        } else {
            consumedEnergy = switch (type) {
                case Primary -> 5 * multiple;
                case Coppery -> 4 * multiple;
                case Metal -> 3 * multiple;
                case Golden -> 2 * multiple;
                case Iridium -> 1;
                default -> 0;
            };
        }
        waterCapacity--;
        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");
    }

    public ToolType getToolType() {
        return type;
    }

    public PoleType getPoleType() {
        return null;
    }

    public int getCapacity() {
        return capacity;
    }


    public int getWaterCapacity() {
        return waterCapacity;
    }

    public boolean isFull() {
        return waterCapacity == capacity;
    }

    public void makeFull() {
        waterCapacity = capacity;
    }

    @Override
    public TextureID getInventoryTexture() {
        return textureRegion;
    }

    @Override
    public String toString() {
        return String.format("WateringCan \n   type: %s, capacity: %d/%d   ", type, waterCapacity, capacity);
    }
}
