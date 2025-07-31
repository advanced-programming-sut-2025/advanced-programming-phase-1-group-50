package com.stardew.model;

public class InventoryItemDTO {
    private final TextureID textureID;
    private final boolean isTool;
    private int quantity;
    private final ItemInventoryType type;
    private final String id;

    public InventoryItemDTO(TextureID textureID, boolean isTool , int quantity , ItemInventoryType type , String id) {
        this.textureID = textureID;
        this.isTool = isTool;
        this.quantity = quantity;
        this.type = type;
        this.id = id;
    }

    public TextureID getTextureID() {
        return textureID;
    }


    public boolean isTool() {
        return isTool;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemInventoryType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

}
