package models.tools;

import models.app.App;

public class MilkPail extends Tool {
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public void useTool() {
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(4);
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
