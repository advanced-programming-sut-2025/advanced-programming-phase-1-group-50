package models.tools;

import models.Result;
import models.app.App;

public class MilkPail extends Tool {
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(4);
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
}
