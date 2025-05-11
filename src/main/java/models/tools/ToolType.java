package models.tools;

public enum ToolType {
    Primary(2000),
    Coppery(5000),
    Metal(10000),
    Golden(25000),
    Iridium(0);
    private final int priceForUpgrade;
    ToolType(int priceForUpgrade) {
        this.priceForUpgrade = priceForUpgrade;
    }
    public int getPriceForUpgrade() {
        return priceForUpgrade;
    }
}
