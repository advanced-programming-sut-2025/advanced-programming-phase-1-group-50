package com.stardew.models.tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
    private TextureRegion hoeTexture = GamePictureManager.hoeTexture;
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
        int consumedEnergy = switch (type) {
            case Primary -> 5;
            case Coppery -> 4;
            case Metal -> 3;
            case Golden -> 2;
            case Iridium -> 1;
            default -> 0;
        };

        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");
    }

    public void upgradeTool() {
        if(this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            this.hoeTexture = GamePictureManager.copperHoeTexture;
        }
        else if(this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            this.hoeTexture = GamePictureManager.steelHoeTexture;
        }
        else if(this.type == ToolType.Metal ){
            this.type = ToolType.Golden;
            this.hoeTexture = GamePictureManager.goldHoeTexture;
        }
        else if(this.type == ToolType.Golden){
            this.type = ToolType.Iridium;
            this.hoeTexture = GamePictureManager.iridiumHoeTexture;
        }



    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }


    @Override
    public TextureRegion getInventoryTexture() {
        return hoeTexture;
    }
}
