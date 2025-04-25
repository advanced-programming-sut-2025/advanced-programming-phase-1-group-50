package models.tools;

public class WateringCan extends Tool {
    private ToolType type = ToolType.Primary;
    private int capacity = 40;
    private void upgradeTool() {
        //changeCapacity
    }

    @Override
    protected int getConsumptionEnergy() {
        return 0;
    }

    @Override
    protected void useTool() {

    }



}
