package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;

import java.util.UUID;

public class Scythe extends Tool {
    private final String id;


    public Scythe() {
        id= UUID.randomUUID().toString();
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
//        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(2);
//        if (!energyConsumptionResult.getSuccessful())
//            return energyConsumptionResult;
//
//        return new Result(true, "");
        return null;

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
        return TextureID.scytheTexture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.scythe , id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Scythe";
    }
}
