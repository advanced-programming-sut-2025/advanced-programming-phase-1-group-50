package models.tools;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
    @Override
    protected int getConsumptionEnergy() {
        return 0;
    }

    @Override
    protected void useTool() {

    }

    public void upgradeTool(){

    }
}
