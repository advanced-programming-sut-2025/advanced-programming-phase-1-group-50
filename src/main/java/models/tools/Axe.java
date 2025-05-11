package models.tools;

public class Axe extends Tool {
    private ToolType type = ToolType.Primary;

    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
        } else if (this.type == ToolType.Metal) {
            this.type = ToolType.Golden;
        } else if (this.type == ToolType.Golden) {
            this.type = ToolType.Iridium;
        }
    }

    @Override
    protected int getConsumptionEnergy() {
        return 0;
    }

    @Override
    protected void useTool() {

    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }

}
