package models.tools;

import models.app.App;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
    @Override
    protected int getConsumptionEnergy() {
        return 0;
    }

    @Override
    protected void useTool() {
        int consumedEnergy = 0 ;
        switch (type) {
            case Primary:
                consumedEnergy = 5;
                break;
            case Coppery:
                consumedEnergy = 4;
                break;
            case Metal:
                consumedEnergy = 3;
                break;
            case Golden:
                consumedEnergy = 2;
                break;
            case Iridium:
                consumedEnergy = 1;
                break;
        }
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);

    }

    public void upgradeTool(ToolType type) {
        this.type = type;


    }
}
