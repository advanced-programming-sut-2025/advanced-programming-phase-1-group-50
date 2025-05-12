package models.tools;

import models.app.App;

public class Scythe extends Tool {
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public void useTool() {
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(2);
    }
    public ToolType getToolType() {
        return null;
    }
    public void upgradeTool(){

    }
    public PoleType getPoleType() {
        return null;
    }


}
