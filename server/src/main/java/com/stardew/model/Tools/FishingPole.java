package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Ability;
import com.stardew.model.userInfo.Player;

import java.util.UUID;

public class FishingPole extends Tool{
    private final PoleType type;
    private final String id;



    public FishingPole(PoleType poleType) {
        this.type = poleType;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool(Weather weather , Player player) {

        int multiple = switch (weather) {
            case Rainy -> 2;
            case Snowy -> 3;
            default -> 1;
        };
        int consumedEnergy;
        if (player.getAbility().getFarmingLevel() == Ability.getMaxLevel()) {
            consumedEnergy = switch (type) {
                case Training, Bamboo -> 7 * multiple;
                case Fiberglass -> 5 * multiple;
                case Iridium -> 3 * multiple;
            };
        } else {
            consumedEnergy = switch (type) {
                case Training, Bamboo -> 8 * multiple;
                case Fiberglass -> 6 * multiple;
                case Iridium -> 4 * multiple;


            };
        }

        Result energyConsumptionResult = player.consumeEnergy(consumedEnergy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");

    }

    @Override
    public void upgradeTool() {
    }

    public PoleType getType() {
        return type;
    }

    public PoleType getPoleType() {
        return type;
    }

    public ToolType getToolType() {
        return null;
    }

    @Override
    public TextureID getInventoryTexture() {
        return switch (type) {
            case Training -> TextureID.trainingFishingPole;
            case Bamboo -> TextureID.bambooFishingPole;
            case Iridium -> TextureID.iridiumFishingPole;
            case Fiberglass -> TextureID.fiberglassFishingPole;
        };
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.fishingPole , id);
    }

    @Override
    public String getId() {
        return id;
    }

    public String toString(){
        return "Fishing Pole " + type.name();
    }
}
