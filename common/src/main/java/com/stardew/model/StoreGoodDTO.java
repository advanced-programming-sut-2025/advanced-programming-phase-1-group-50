package com.stardew.model;

public class StoreGoodDTO {

    private final String productName;
    private final int price;
    private final int quantity;
    private final boolean instanceOfMarnieRanchLiveStockItem;
    private final boolean instanceOfCarpenterShopFarmBuildingsItem;

    public StoreGoodDTO(String productName, int price, int quantity, boolean isMarnieRanchLiveStockItem, boolean isCarpenterShopFarmBuildings) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.instanceOfMarnieRanchLiveStockItem = isMarnieRanchLiveStockItem;
        this.instanceOfCarpenterShopFarmBuildingsItem = isCarpenterShopFarmBuildings ;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isInstanceOfMarnieRanchLiveStockItem() {
        return instanceOfMarnieRanchLiveStockItem;
    }

    public boolean isInstanceOfCarpenterShopFarmBuildingsItem() {
        return instanceOfCarpenterShopFarmBuildingsItem;
    }
}
