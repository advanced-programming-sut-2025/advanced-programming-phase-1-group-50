package com.stardew.models.tools;

import com.stardew.models.Result;
import com.stardew.models.app.App;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
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
        }
        else if(this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
        }
        else if(this.type == ToolType.Metal ){
            this.type = ToolType.Golden;
        }
        else if(this.type == ToolType.Golden){
            this.type = ToolType.Iridium;
        }



    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }
}
