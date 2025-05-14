package models.tools;

public abstract class Tool {
    protected ToolType toolType;
    protected PoleType poleType;
    protected String name;

    protected abstract int getConsumptionEnergy();
    protected abstract void useTool();
    public abstract void upgradeTool();
    public abstract ToolType getToolType();
    public abstract PoleType getPoleType();

    public static Tool getToolByName(String name) {
        return switch (name.toLowerCase()) {
            case "axe" -> new Axe();
            case "fishingpole" -> new FishingPole();
            case "hoe" -> new Hoe();
            case "milkpail" -> new MilkPail();
            case "pickaxe" -> new Pickaxe();
            case "scythe" -> new Scythe();
            case "shear" -> new Shear();
            case "wateringcan" -> new WateringCan();
            default -> null;
        };
    }
}
