package com.stardew.models.tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;

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
    public TextureRegion getInventoryTexture() {
        return new TextureRegion(GamePictureManager.scytheTexture);
    }
}
