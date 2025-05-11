package models.tools;

import models.manuFactor.Ingredient;

public abstract class Tool {
    protected ToolType toolType;
    protected PoleType poleType;
    protected String name;

    protected abstract int getConsumptionEnergy();
    protected abstract void useTool();
    public abstract void upgradeTool();
    public abstract ToolType getToolType();
    public  abstract PoleType getPoleType();
}
