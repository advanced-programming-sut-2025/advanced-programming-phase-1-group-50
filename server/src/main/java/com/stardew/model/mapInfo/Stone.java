package com.stardew.model.mapInfo;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.TextureID;

import java.awt.*;
import java.util.UUID;

public class Stone implements Placeable , Ingredient{

    private final String id;



    private Rectangle bounds;
    private final TextureID texture = TextureID.farmBoulderTexture;

    public Stone() {
        id = UUID.randomUUID().toString();
    }

    public Stone(int x, int y) {
        this.bounds = new Rectangle(x, y, 1, 1);
        this.id = UUID.randomUUID().toString();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return 'S';
    }

    @Override
    public TextureID getTexture() {
        return texture;
    }

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Stone;
    }



    @Override
    public String toString() {
        return "Stone";
    }







    @Override
    public TextureID getInventoryTexture() {
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , false , 1 , ItemInventoryType.stone , id);
    }

    @Override
    public String getId() {
        return id;
    }


}
