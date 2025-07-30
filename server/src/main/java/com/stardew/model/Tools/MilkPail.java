package com.stardew.model.Tools;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;

public class MilkPail extends Tool{
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
        // TODO : add lobby id
//        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(4);
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
        return TextureID.milkPailTexture;
    }

    public String toString(){
        return "Milk Pail";
    }
}
