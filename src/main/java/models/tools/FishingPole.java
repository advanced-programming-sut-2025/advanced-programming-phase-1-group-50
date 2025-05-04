package models.tools;

public class FishingPole extends Tool {
    private PoleType type = PoleType.Training;

    private void upgradeTool() {

    }

    @Override
    protected int getConsumptionEnergy() {
        return 0;
    }

    @Override
    protected void useTool() {

    }

    public PoleType getType() {
        return type;
    }

    public void setType(PoleType type) {
        this.type = type;
    }
}
