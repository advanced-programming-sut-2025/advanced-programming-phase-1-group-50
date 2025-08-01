package com.stardew.models.tools;

import com.stardew.models.InventoryItem;
import com.stardew.models.Result;

public abstract class Tool implements InventoryItem {
    protected ToolType toolType;
    protected PoleType poleType;
    protected String name;

    public abstract int getConsumptionEnergy();
    public abstract Result useTool();
    public abstract void upgradeTool();
    public abstract ToolType getToolType();
    public abstract PoleType getPoleType();

    public static Tool getToolByName(String name) {
        return switch (name.toLowerCase()) {
            case "axe" -> new Axe();
            case "fishingpole" -> new FishingPole(PoleType.Training);
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
