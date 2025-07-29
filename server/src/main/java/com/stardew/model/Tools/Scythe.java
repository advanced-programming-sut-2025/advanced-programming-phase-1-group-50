package com.stardew.model.Tools;

import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;

public class Scythe extends Tool {
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(2);
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
        return TextureID.scytheTexture;
    }

    @Override
    public String toString() {
        return "Scythe";
    }
}
