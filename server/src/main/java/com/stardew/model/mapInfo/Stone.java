package com.stardew.model.mapInfo;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;

import java.awt.*;

public class Stone implements Placeable , Ingredient{



    private Rectangle bounds;
    private final TextureID texture = TextureID.farmBoulderTexture;

    public Stone() {

    }

    public Stone(int x, int y) {
        this.bounds = new Rectangle(x, y, 1, 1);
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


}
