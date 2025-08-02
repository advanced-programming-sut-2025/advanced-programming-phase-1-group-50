package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Player;

import java.util.UUID;

public class MilkPail extends Tool{
    private final String id;

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    public MilkPail(){
        id = UUID.randomUUID().toString();
    }

    @Override
    public Result useTool(Weather weather , Player player) {
        // TODO : add lobby id
        Result energyConsumptionResult = player.consumeEnergy(4);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");

    }

    public ToolType getToolType() {
        return null;
    }

    public void upgradeTool() {

    }

    public PoleType getPoleType() {
        return null;
    }

    @Override
    public TextureID getInventoryTexture() {
        return TextureID.milkPailTexture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.milkPail , id);
    }

    @Override
    public String getId() {
        return id;
    }

    public String toString(){
        return "Milk Pail";
    }
}
