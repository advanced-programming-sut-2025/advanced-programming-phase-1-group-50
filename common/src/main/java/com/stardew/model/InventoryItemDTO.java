package com.stardew.model;

public class InventoryItemDTO {
    private final TextureID textureID;
    private final boolean isTool;
    private int quantity;
    private final String description;
    private final String id;

    public InventoryItemDTO(TextureID textureID, boolean isTool, int quantity, String description, String id) {
        this.textureID = textureID;
        this.isTool = isTool;
        this.quantity = quantity;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return description;
    }

}
