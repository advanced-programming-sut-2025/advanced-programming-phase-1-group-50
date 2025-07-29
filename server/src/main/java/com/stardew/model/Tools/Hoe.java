package com.stardew.model.Tools;

import com.stardew.model.Result;
import com.stardew.model.TextureID;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
    private TextureID hoeTexture = TextureID.hoeTexture;
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

//        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
//        if (!energyConsumptionResult.getSuccessful())
//            return energyConsumptionResult;

        return new Result(true, "");
    }

    public void upgradeTool() {
        if(this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            this.hoeTexture = TextureID.copperHoeTexture;
        }
        else if(this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            this.hoeTexture = TextureID.steelHoeTexture;
        }
        else if(this.type == ToolType.Metal ){
            this.type = ToolType.Golden;
            this.hoeTexture = TextureID.goldHoeTexture;
        }
        else if(this.type == ToolType.Golden){
            this.type = ToolType.Iridium;
            this.hoeTexture = TextureID.iridiumHoeTexture;
        }



    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }


    @Override
    public TextureID getInventoryTexture() {
        return hoeTexture;
    }

    @Override
    public String toString() {
        return "Hoe " + type.name();
    }
}
