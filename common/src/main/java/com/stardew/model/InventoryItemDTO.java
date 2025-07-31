package com.stardew.model;

public class InventoryItemDTO {
    private final TextureID textureID;
    private final boolean isTool;
    private int quantity;

    public InventoryItemDTO(TextureID textureID, boolean isTool , int quantity) {
        this.textureID = textureID;
        this.isTool = isTool;
        this.quantity = quantity;
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

}
