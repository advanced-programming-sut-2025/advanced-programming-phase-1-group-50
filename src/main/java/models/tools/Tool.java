package models.tools;

public abstract class Tool {
    protected String name;

    protected abstract int getConsumptionEnergy();
    protected abstract void useTool();
}
